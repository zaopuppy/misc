
-- x axis in horizontal
-- y axis in vertical

-- constant
g_grid_width = 30
g_grid_height = 30

-- variable
g_columns = 0
g_rows = 0
g_scr_width = 800
g_scr_height = 600

g_scr_offset_x = 0 + g_grid_width*3
g_scr_offset_y = 0 + g_grid_height*3

g_marked_grids = {}

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
    for k, v in pairs(g_marked_grids) do
        local c, r = v[1], v[2]
        love.graphics.rectangle("fill",
            g_scr_offset_x + (c*g_grid_width),
            g_scr_offset_y + (r*g_grid_height),
            g_grid_width, g_grid_height)
    end
    -- draw position for makred grids
    love.graphics.setColor(1, 1, 1)
    for k, v in pairs(g_marked_grids) do
        local c, r = v[1], v[2]
        love.graphics.print(string.format("(%d, %d)", c, r),
            g_scr_offset_x + (c*g_grid_width),
            g_scr_offset_y + (r*g_grid_height))
    end
end


function love.mousemoved(x, y, dx, dy, istouch)
    g_mouse_x, g_mouse_y = x, y
end

function love.mousepressed(x, y, button, istouch, presses)
    x = x - g_scr_offset_x
    y = y - g_scr_offset_y
    local col, row = math.floor(x/g_grid_width), math.floor(y/g_grid_height)
    if button == 1 then
        -- mark grid
        local idx = find_idx(g_marked_grids, col, row)
        if idx < 0 then
            table.insert(g_marked_grids, {col, row})
        end
    elseif button == 2 then
        -- unmark grid
        local idx = find_idx(g_marked_grids, col, row)
        if idx >= 0 then
            table.remove(g_marked_grids, idx)
        end
    end
end

function find_idx(grids, col, row)
    for k, v in pairs(g_marked_grids) do
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
    end
end

