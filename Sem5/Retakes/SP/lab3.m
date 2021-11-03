clc
clear all

%        Problem 1

alpha = input('alpha = ');
beta = input('beta = ');
option = input('Choose option: normal, student, Chi2, Fischer','s');
switch option
  case 'normal'
    %  X~Norm(mu,sigma)    normal distribution
    disp('Normal distribution')
    mu=input('mu=');
    sigma=input('sigma=');
    Pa1=normcdf(0 ,mu,sigma);
    Pa2=1 - Pa1;
    Pb1=normcdf(-1,mu,sigma) * (1-normcdf(1,mu,sigma);
    Pb2=1 - Pb1;
    Pc=norminv(alpha,mu,sigma);  %P(X<=x)=alpha =>  x=?  
                  %P(X<=x)=normcdf(x)=alpha  => x=normcdf^(-1)(alpha)  
                  %                               normcdf^{-1)=norminv
    Pd=norminv(1-beta, mu ,sigma);

  case 'student'
    %  X~T(n)    student distribution
    disp('Student distribution')
    n=input('n=')
    Pa1=tcdf( ??? ,n);
    ...
    Pc=tinv( ??? ,n);
    Pd=...;

  case 'Chi2'
    %  X~Chi2(k)    Chi2 distribution
    disp('Chi2 distribution')
    k=...;
    Pa1=chi2cdf(???,...);
    ...
    Pc=chi2inv(alpha,...);
    Pd=...;

  case 'Fischer'
    %  X~F(m,n)    Fisher distribution
    disp('Fisher distribution')
    m=...; n=...;
    Pa1=fcdf(???,???,???);
    ...
    Pc=finv(?,?,?);
    ...
  otherwise
    fprintf('Wrong option!')
end

% Displaying the results.
fprintf('First probability in part a) is: %1.5f \n', Pa1)
....
fprintf('Answer in part d) x_beta is: %1.5f \n', Pd)



%        Problem 2

p = ...    %0.05 <= p <= 0.95
for n = 1 : 3 : 100 % or n = 1:2:100, or n = 1:5:100
    Bino_pdf = binopdf(0 : n, n, p);
    plot(0 : n, Bino_pdf ,'b')
    
    hold on
    ylim([0 0.8]);
    
    m=...; s=...;
    Norm_pdf=normpdf(0:0.1:n, ? , ? );
    plot( ??? , ??? ,'r--')
    hold off
    pause(0.5)
end
legend('Binomial', 'Normal')

% For Poisson pdf we have poisspdf(???,lambda)