pkg load statistics
clc
clear all

%  a) 

% Generate X~Bernoulli(p)    <=>    X ~ (0     1)
                                      % (1-p   p)
p=0.3

u=rand;    % unifrnd(0,1)      
         
% X= { 1,  if u < p
%    { 0,  if u >= p             
X=(u < p); 


%  Verification
N=1000;
for i=1:N
  U=rand;
  Bernoulli(i)=(U<p);
end
 
Frq_a=hist(Bernoulli,0:1);
Frq_r=Frq_a/N;
disp('Estimated Bernoulli distribution')
disp([0:1; Frq_r])
disp('Theoretical Bernoulli distribution')
disp([0:1; binopdf(0:1,1,p)])

bar(0:1 , Frq_r, 'b')
hold on
bar(0:1 , binopdf(0:1,1,p), 'y')
legend('Estimated','Theoretical')
set(findobj('type','patch'), 'facealpha',0.7)


% b)
% Generate X~Bino(n,p)  <=>  X ~ (0     1     2   3  ....  n  )
%                                (P(0) P(1) P(2) P(3) ... P(n))
% Binomial r.v. =sum of n Bernoully r.v X (counts the number of ones)
% to compare use:    0:n          binopdf(0:n,n,p)

n = 5;
x = (rand(1,n) < p);
Bino = sum(x)

% c) 
% Generate X~Geo(p)  <=>  X ~ (0     1     2   3  ....  k   ...  )
%                             (P(0) P(1) P(2) P(3) ... P(k) ...  )
% Geometric r.v.(counts the number of zeros before first one) 
% o compare use:    0:k     geopdf(0:k,p)   k positive integer

% d)
% Generate X~Nbin(n,p)  <=>  X ~ (0     1     2   3  ....  k  )
%                                (P(0) P(1) P(2) P(3) ... P(k))
%  Pascal (NBin) r.v. = sum of n Geometric r.v. 
%  to compare use:   0:k    nbin(0:k,n,p)   k also positive integer
