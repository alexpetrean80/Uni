import Prelude
import System.Random


-- generate a t-bounded random list of n elements
rList :: (RandomGen g, Random a) => g -> (a,a) -> Int -> [a]
rList s t n = take n $ randomRs t s

-- binary exponentiation
pow :: (Integral a) => a -> a -> a -> a
pow a 1 n = a
pow a e n = mod (a ^ (mod e 2) * (pow (mod (a * a) n) (div e 2))) n

twoPowFact :: (Integral a) => a -> (a, a)
twoPowFact 0 = (0,0)
twoPowFact m = let go (s,n)
                | even n = go (s + 1, div n 2)
                | otherwise = (s, n)
              in go (0, abs m)

millerRabin :: (Integral a) => a -> a -> Bool
millerRabin n a = let fact = twoPowFact (n - 1);
                      base = pow a (snd fact) n;
                      test b s r n
                        | r == s = b == n - 1
                        | b == 1 && r /= 0 = False  
                        | otherwise = b == n - 1 || test (pow b 2 n) s (r + 1) n
                  in base == 1 || test base (fst fact) 0 n
