/*
    LESSON 5 - SHADERY, KOLOROWANIE
*/
#version 400 core

in vec3 position; // wejście

out vec3 colour; // wyjście 

void main(void){
    
    gl_Position  = vec4(position, 1.0);
    colour = vec3(position.x + 0.5, 0.3, position.y + 0.5);
}

