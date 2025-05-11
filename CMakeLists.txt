cmake_minimum_required(VERSION 3.10)
project(Compiladores)

include_directories(include)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)


set(SOURCE_FILES
    src/Scanner.cpp
    src/Token.cpp
)

set(HEADER_FILES
    include/Scanner.h
    include/Token.h
)

add_executable(${PROJECT_NAME}.exe app/main.cpp ${SOURCE_FILES})