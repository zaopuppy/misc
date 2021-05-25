
-- x axis in horizontal
-- y axis in vertical

G = {}

-- constant
g_grid_width = 30
g_grid_height = 30
g_pointer_radius = 5

-- variable
g_columns = 0
g_rows = 0
g_scr_width = 800
g_scr_height = 600

g_scr_offset_x = 0 + g_grid_width*3
g_scr_offset_y = 0 + g_grid_height*3

g_marked_pointers = {}
g_selected_pinters = {}

--
g_mouse_x = 0
g_mouse_y = 0

-- Load some default values for our rectangle.
function love.load()
    update_col_and_row(g_scr_width, g_scr_height)
    love.window.setTitle("Grids")
    love.window.setMode(g_scr_width, g_scr_height, {resizable=true})
end

function update_col_and_row(width, height)
    g_scr_width, g_scr_height = width, height
    g_columns = math.ceil(g_scr_width/g_grid_width)
    g_rows = math.ceil(g_scr_height/g_grid_height)
end

-- Increase the size of the rectangle every frame.
function love.update(dt)
end

-- Draw a coloured rectangle.
function love.draw()
    -- In versions prior to 11.0, color component values are (0, 102, 102)
    -- love.graphics.rectangle("fill", x, y, w, h)

    -- draw grids
    love.graphics.setColor(0, 0.4, 0.4)
    for c = 1, g_columns do
        local x1 = (c-1)*g_grid_width
        local y1 = 0
        local x2 = x1
        local y2 = g_scr_height
        love.graphics.line(
            g_scr_offset_x + x1,
            g_scr_offset_y + y1,
            g_scr_offset_x + x2,
            g_scr_offset_y + y2)
    end
    for r = 1, g_rows do
        local x1 = 0
        local y1 = (r-1)*g_grid_height
        local x2 = g_scr_width
        local y2 = y1
        love.graphics.line(
            g_scr_offset_x + x1,
            g_scr_offset_y + y1,
            g_scr_offset_x + x2,
            g_scr_offset_y + y2)
    end

    -- draw marked grids
    for k, v in ipairs(g_marked_pointers) do
        local c, r = v[1], v[2]
        love.graphics.ellipse("fill",
            g_scr_offset_x + (c*g_grid_width),
            g_scr_offset_y + (r*g_grid_height),
            g_pointer_radius, g_pointer_radius)
    end
    -- draw position for marked grids
    love.graphics.setColor(1, 1, 1)
    for k, v in ipairs(g_marked_pointers) do
        local c, r = v[1], v[2]
        love.graphics.print(string.format("(%d, %d)", c, r),
            g_scr_offset_x + (c*g_grid_width),
            g_scr_offset_y + (r*g_grid_height))
    end

    draw_alorithm_info()
end


function love.mousemoved(x, y, dx, dy, istouch)
    g_mouse_x, g_mouse_y = x, y
end

function love.mousepressed(x, y, button, istouch, presses)
    x = x - g_scr_offset_x
    y = y - g_scr_offset_y
    local near_x = math.floor((x+(g_grid_width/2))/g_grid_width)
    local near_y = math.floor((y+(g_grid_height/2))/g_grid_height)
    if button == 1 then
        -- mark grid
        mark_pointer(g_marked_pointers, near_x, near_y)
    elseif button == 2 then
        -- unmark grid
        unmark_pointer(g_marked_pointers, near_x, near_y)
    end
end

function mark_pointer(pointers, x, y)
    local idx = find_idx(pointers, x, y)
    if idx < 0 then
        table.insert(pointers, {x, y})
    end
end

function unmark_pointer(pointers, x, y)
    local idx = find_idx(pointers, x, y)
    if idx >= 0 then
        table.remove(pointers, idx)
    end
end

function find_idx(grids, col, row)
    for k, v in ipairs(g_marked_pointers) do
        local c, r = v[1], v[2]
        if c == col and r == row then
            return k
        end
    end
    return -1
end

function love.resize(w, h)
    update_col_and_row(w, h)
end

function love.keypressed(key, scancode, isrepeat)
    if key == 'q' then
        love.event.quit()
    elseif key == 'w' or key == 'up' then
        g_scr_offset_y = g_scr_offset_y - math.floor(g_grid_height/2)
    elseif key == 's' or key == 'down' then
        g_scr_offset_y = g_scr_offset_y + math.floor(g_grid_height/2)
    elseif key == 'a' or key == 'left' then
        g_scr_offset_x = g_scr_offset_x - math.floor(g_grid_width/2)
    elseif key == 'd' or key == 'right' then
        g_scr_offset_x = g_scr_offset_x + math.floor(g_grid_width/2)
    elseif key == 'space' then
        start_or_resume_algorithm()
    elseif key == 'lctrl' then
        debug.debug()
    end
end


-----------------------------------------------------------------------------
-- algorithm

local G_A = {
    co = nil,
    current = nil
}

function start_or_resume_algorithm()
    -- local result = bfs_non_recursive(15, 15, 0, 0)
    -- local result = dfs_non_recursive(6, 6, 0, 0)
    -- print("result=" .. tostring(result))
    -- local finished, p, result = dfs_i(5000, 5000, 0, 0)
    -- if finished then
    --     print("final result: " .. tostring(result))
    -- end
    if not G_A.co then
        G_A.co = coroutine.create(dfs)
        local resumed, finished, p, result = coroutine.resume(G_A.co, 9, 5, 0, 0)
        G_A.current = p
        print("result=" .. tostring(result))
        if finished then
            print("final result: " .. tostring(result))
            G_A.co = nil
        end
    else
        local resumed, finished, p, result = coroutine.resume(G_A.co)
        G_A.current = p
        print("result=" .. tostring(result))
        if finished then
            print("final result: " .. tostring(result))
            G_A.co = nil
        end
    end
end

function draw_alorithm_info()
    if not G_A.current then
        return
    end

    love.graphics.setColor(1, 0, 0)
    local c, r = G_A.current[1], G_A.current[2]
    love.graphics.ellipse("fill",
        g_scr_offset_x + (c*g_grid_width),
        g_scr_offset_y + (r*g_grid_height),
        g_pointer_radius, g_pointer_radius)
end

function calc_em(x1, y1, x2, y2)
    return (x1-x2)^2 + (y1-y2)^2
end

-- (x1, y1) --> (x2, y2)
function next_pos(x1, y1, x2, y2, marks)
    local cur_distance = calc_em(x1, y1, x2, y2)
    local result = {}
    local candidates = {
        {x1 +  2, y1 +  1},
        {x1 +  1, y1 +  2},
        {x1 + -2, y1 +  1},
        {x1 +  1, y1 + -2},
        {x1 +  2, y1 + -1},
        {x1 + -1, y1 +  2},
        {x1 + -2, y1 + -1},
        {x1 + -1, y1 + -2}
    }
    for k, v in ipairs(candidates) do
        if calc_em(v[1], v[2], x2, y2) < cur_distance then
            table.insert(result, v)
        end
    end
    return result
end

-- (x1, y1) --> (x2, y2)
-- (finished, coor, result)
function dfs(x1, y1, x2, y2, marks)
    if not marks then
        marks = {}
    end

    print(string.format("(%d, %d) -> (%d, %d)", x1, y1, x2, y2))

    coroutine.yield(false, {x1, y1}, false)

    -- arrived
    if x1 == x2 and y1 == y2 then
        print("arrived")
        return true, {x1, y1}, true
    end

    -- distance 1, never win
    -- if (math.abs(x1 - x2) and y1 == y2) or () then
    --     return false
    -- end

    for k, v in ipairs(next_pos(x1, y1, x2, y2, marks)) do
        local finished, coor, result = dfs(v[1], v[2], x2, y2, marks)
        -- print(tostring(ret))
        if result then
            print("perfect")
            return true, {x1, y1}, true
        end
    end

    return true, {x1, y1}, false
end

function dfs_i(x1, y1, x2, y2, marks)
    if not marks then
        marks = {}
    end

    -- print(string.format("(%d, %d) -> (%d, %d)", x1, y1, x2, y2))

    -- arrived
    if x1 == x2 and y1 == y2 then
        print("arrived")
        return true, {x1, y1}, true
    end

    -- distance 1, never win
    if (math.abs(x1 - x2) and y1 == y2) or
       (math.abs(y1 - y2) and x1 == x2) then
        return false
    end

    for k, v in ipairs(next_pos(x1, y1, x2, y2, marks)) do
        local finished, coor, result = dfs_i(v[1], v[2], x2, y2, marks)
        -- print(tostring(ret))
        if result then
            print("perfect")
            return true, {x1, y1}, true
        end
    end

    return true, {x1, y1}, false
end

function gen_key(x, y)
    return x .. ',' .. y
end


function meet_condition(x1, y1, x2, y2)
    if x1 == x2 and y1 == y2 then
        return true
    end

    -- distance 1, never win
    if (math.abs(x1 - x2) == 1 and y1 == y2) or
       (math.abs(y1 - y2) == 1 and x1 == x2) then
        return true
    end

    return false
end


-- (100, 100):       71,932
-- (1000, 1000): 68,715,487
function bfs_non_recursive(x1, y1, x2, y2)

    local count = 1
    local pointers = {}

    pointers[gen_key(x1, y1)] = {x1, y1}
    table.insert(g_marked_pointers, {x1, y1})

    while not not next(pointers) do
        for _, v in pairs(pointers) do
            local x, y = v[1], v[2]
            if meet_condition(x, y, x2, y2) then
                print("meet: " .. count)
                return true
            end
        end

        local new_pointers = {}
        for _, v in pairs(pointers) do
            local x, y = v[1], v[2]
            -- print(x, y)
            for _, v1 in ipairs(next_pos(x, y, x2, y2)) do
                local k = gen_key(v1[1], v1[2])
                if not new_pointers[k] then
                    new_pointers[k] = v1
                    table.insert(g_marked_pointers, v1)
                    count = count + 1
                end
            end
        end

        pointers = new_pointers
    end

    print("too bad: " .. count)
    return false
end

function dfs_non_recursive(start_x, start_y, target_x, target_y)
    print("dfs_non_recursive: " .. start_x .. ', ' .. start_y)

    local pointers = { {start_x, start_y} }
    local count = 1

    while not not next(pointers) do
        -- add pointer around
        local new_pointers = {}
        for k, v in ipairs(pointers) do
            local x, y = v[1], v[2]
            -- print(x, y)
            for k1, v1 in ipairs(next_pos(x, y, target_x, target_y)) do
                table.insert(new_pointers, v1)
                count = count + 1
            end
        end

        pointers = new_pointers
    end

    print("too bad: " .. count)
    return false
end


