
#include <stdio.h>
#include <io.h>
#include <fcntl.h>

#include <memory>

#include <Windows.h>

#include <SDL.h>
#include <SDL_image.h>

using namespace std;


#define LOG(fmt, ...) printf(fmt ## "\n", __VA_ARGS__)
// #define LOG(s) OutputDebugStringA(s)


const int WINDOW_WIDTH = 800;
const int WINDOW_HEIGHT = 600;


char* create_image() {
    char *buf = new char[WINDOW_WIDTH*WINDOW_HEIGHT*3];
    for (int x = 0; x < WINDOW_WIDTH; ++x) {
        for (int y = 0; y < WINDOW_HEIGHT; ++y) {
            buf[x+y] = 0x00;
        }
    }
    return buf;
}


void draw(SDL_Surface *surface) {
    char *img = create_image();

    // TODO

    delete img;
}


void open_console() {
    AllocConsole();

    FILE *stdin_stream;
    freopen_s(&stdin_stream, "conin$", "r+t", stdin);

    FILE *stdout_stream;
    freopen_s(&stdout_stream, "conout$", "w+t", stdout);

    // don't forget to free console
    // FreeConsole();
    // fclose(stdin_stream);
    // fclose(stdout_stream);
}


// int main(int argc, char* argv[]) {
int WINAPI wWinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, PWSTR pCmdLine, int nCmdShow) {

    open_console();

    LOG("Just a test.");

    SDL_Init(SDL_INIT_VIDEO);
    // SDL_Init(0);

    SDL_Window* window = SDL_CreateWindow("light2d",
        SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED,
        WINDOW_WIDTH, WINDOW_HEIGHT, SDL_WINDOW_RESIZABLE);

    SDL_SetHint(SDL_HINT_RENDER_SCALE_QUALITY, "linear");

    // auto renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED);

    SDL_Surface* surface = SDL_GetWindowSurface(window);


    bool running = true;
    SDL_Event event;
    while (running) {
        if (SDL_PollEvent(&event) != 0) {
            switch (event.type) {
                case SDL_QUIT: {
                    LOG("sdl quit");
                    running = false;
                    break;
                }
                case SDL_KEYDOWN: {
                    LOG("key down");
                    SDL_FillRect(surface, NULL, SDL_MapRGB(surface->format, 0xFF, 0x0, 0xFF));
                    break;
                }
            }
        }

        SDL_UpdateWindowSurface(window);
    }

    // SDL_Delay(2000);

    SDL_FreeSurface(surface);

    SDL_DestroyWindow(window);

    SDL_Quit();

    return 0;
}

