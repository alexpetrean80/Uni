x = 0 : 0.001 : 3;

F = x .^ 5 / 10;

G = x .* sin(x);

H = cos(x);

subplot(2,2,1);
plot(x, F);

subplot(2,2,2);
plot(x, G);

subplot(2,2,3);
plot(x, H);
