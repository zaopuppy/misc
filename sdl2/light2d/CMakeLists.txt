
cmake_minimum_required(VERSION 3.10)

# set the project name
project(light2d)

# SDL2
include_directories("d:\\dev\\sdk\\sdl2\\SDL2-2.0.14\\include")
link_directories("d:\\dev\\sdk\\sdl2\\SDL2-2.0.14\\lib\\x64")

# SDL2
include_directories("d:\\dev\\sdk\\sdl2\\SDL2_image-2.0.5\\include")
link_directories("d:\\dev\\sdk\\sdl2\\SDL2_image-2.0.5\\lib\\x64")

# add the executable
add_executable(light2d WIN32 main.cc)

target_link_libraries(light2d SDL2)

set(CMAKE_VERBOSE_MAKEFILEON ON)

