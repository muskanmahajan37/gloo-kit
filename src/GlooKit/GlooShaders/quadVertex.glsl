#version 330 core

uniform mat4 projectionMatrix;

in vec2 in_Position;
in vec4 in_Color;
in vec2 in_TextureCoord;

out vec4 pass_Color;
out vec2 pass_TextureCoord;

void main(void) {
    gl_Position = projectionMatrix * vec4(in_Position, 0, 1);

    pass_Color = in_Color;
    pass_TextureCoord = in_TextureCoord;
}