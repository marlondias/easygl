#version 120

varying vec3 exColor;

void main()
{
	gl_FragColor = vec4(exColor, 1.0);
}