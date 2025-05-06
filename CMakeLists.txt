cmake_minimum_required(VERSION 3.10)
project(Compiladores)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)


set(SOURCE_FILES
    src/Operator.cpp
    src/Manager.cpp
    src/Wrapper.cpp
)

set(HEADER_FILES
    include/Operator.h
    include/Manager.h
    include/Wrapper.h
)

add_executable(${PROJECT_NAME}.exe app/main.cpp)