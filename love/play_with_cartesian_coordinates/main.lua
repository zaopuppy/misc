
-- x axis in horizontal
-- y axis in vertical

-- constant
g_pointer_radius = 2
g_scale = 10

-- variable
g_scr_width = 800
g_scr_height = 600

g_scr_offset_x = math.floor(g_scr_width/2)
g_scr_offset_y = math.floor(g_scr_height/2)

g_marked_pointers = {}
g_selected_pinters = {}

--
g_mouse_x = 0
g_mouse_y = 0


g_anime_interval = 0.5

g_run_anime = false
g_delta = 0


-- Load some default values for our rectangle.
function love.load()
    update_screen_size(g_scr_width, g_scr_height)
    love.window.setTitle("Cartesion Coordinates System")
    love.window.setMode(g_scr_width, g_scr_height, {resizable=true})
end

function update_screen_size(width, height)
    g_scr_width, g_scr_height = width, height
end

-- Increase the size of the rectangle every frame.
function love.update(dt)
    if g_run_anime then
        g_delta = g_delta + dt
        if g_delta > g_anime_interval then
            g_delta = g_delta % g_anime_interval
            if g_delta < 0.0001 then
                g_delta = 0
            end
            start_or_resume_algorithm()
        end
    end
end

-- Draw a coloured rectangle.
function love.draw()
    -- In versions prior to 11.0, color component values are (0, 102, 102)
    -- love.graphics.rectangle("fill", x, y, w, h)

    love.graphics.translate(g_scr_offset_x, g_scr_offset_y)

    -- draw coordinates
    love.graphics.setColor(0, 0.4, 0.4)
    -- x axis
    love.graphics.line(-g_scr_offset_x, 0, g_scr_width, 0)
    -- y axis
    love.graphics.line(0, -g_scr_offset_y, 0, g_scr_height)
    -- origin
    love.graphics.setColor(1, 1, 0)
    love.graphics.ellipse("fill", 0, 0, g_pointer_radius, g_pointer_radius)
    love.graphics.print("(0, 0)", 0, 0)

    -- draw position for marked pointers
    love.graphics.setColor(1, 1, 1)
    for k, v in ipairs(g_marked_pointers) do
        local x, y = v[1], v[2]
        love.graphics.ellipse("fill", x*g_scale, y*g_scale, g_pointer_radius, g_pointer_radius)
        love.graphics.print(string.format("(%d, %d)", x, y), x*g_scale, y*g_scale)
    end

    draw_alorithm_info()
end


function love.mousemoved(x, y, dx, dy, istouch)
    g_mouse_x, g_mouse_y = x, y
end

function love.mousepressed(x, y, button, istouch, presses)
    x = x - g_scr_offset_x
    y = y - g_scr_offset_y
    local near_x = math.floor((x + g_scale/2) / g_scale)
    local near_y = math.floor((y + g_scale/2) / g_scale)
    if button == 1 then
        -- mark
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

function find_idx(pointers, x, y)
    for k, v in ipairs(pointers) do
        local xx, yy = v[1], v[2]
        if x == xx and y == yy then
            return k
        end
    end
    return -1
end

function love.resize(w, h)
    update_col_and_row(w, h)
end

function love.keypressed(key, scancode, isrepeat)
    local step = 10
    if key == 'q' then
        love.event.quit()
    elseif key == 'w' or key == 'up' then
        g_scr_offset_y = g_scr_offset_y - step
    elseif key == 's' or key == 'down' then
        g_scr_offset_y = g_scr_offset_y + step
    elseif key == 'a' or key == 'left' then
        g_scr_offset_x = g_scr_offset_x - step
    elseif key == 'd' or key == 'right' then
        g_scr_offset_x = g_scr_offset_x + step
    elseif key == 'space' then
        -- start_or_resume_algorithm()
        g_run_anime = not g_run_anime
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
    -- if not G_A.co then
    --     G_A.co = coroutine.create(do_search)
    -- end

    -- local resumed = coroutine.resume(G_A.co)
    -- if not resumed then
    --     G_A.co = nil
    -- end
end

function draw_alorithm_info()
end
