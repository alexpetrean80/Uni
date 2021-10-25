import Data.List
import Data.Tuple

import qualified Data.Map as Map

-- implementation of the modulo operation
modulo :: Int -> Int -> Int
modulo a b = a - (quot a b) * b
 
-- subtraction based algorithm
gcdOne :: Int -> Int -> Int
gcdOne a b
  | a == b = a
  | a > b = gcdOne (a - b) b
  | otherwise = gcdOne a (b - a)

-- division based algorithm
gcdTwo :: Int -> Int -> Int
gcdTwo a b
  | b == 0 = a
  | otherwise = gcdTwo b (modulo a b)

-- BRUTE FORCE
gcdThree :: Int -> Int -> Int
gcdThree a b = gcd' a b (min a b)
  where 
    gcd' :: Int -> Int -> Int -> Int
    gcd' a b x 
      | (modulo a x) == 0 && (modulo b x) == 0 = x
      | otherwise = gcd' a b (x - 1)
   

-- implementation with recursive rule
gcdManyNumbersOne :: [Int] -> Int
gcdManyNumbersOne ([]) = 0
gcdManyNumbersOne (nr : []) = nr
gcdManyNumbersOne (nr : nr' : []) = gcdOne nr nr'
gcdManyNumbersOne (nr : nrs) = gcdOne nr (gcdManyNumbersOne nrs)

-- BRUTE FORCE
checkEqual :: [Int] -> Bool
checkEqual (nr : []) = False
checkEqual (nr : nr' : []) = nr == nr'
checkEqual (nr : nrs) = nr == head nrs && (checkEqual nrs)

moduloMany :: [Int] -> Int -> [Int]
moduloMany (nr : []) x = [modulo nr x]
moduloMany (nr : nrs) x = (moduloMany [nr] x) ++ (moduloMany nrs x)

gcdManyNumbersTwo :: [Int] -> Int
gcdManyNumbersTwo nrs = gcdMany' nrs (minimum nrs)
  where
    gcdMany' :: [Int] -> Int -> Int
    gcdMany' nrs r
      | checkEqual (moduloMany nrs r) = r
      | otherwise = gcdMany' nrs (r - 1) 


-- implementation with decomposition Into prime factors
isPrime :: Int -> Bool
isPrime n = isPrime' n 2
  where 
    isPrime' :: Int -> Int -> Bool
    isPrime' n nr 
      | n < 2 = False
      | n == 2 = True
      | n == 3 = True
      | (modulo n nr) == 0 = False
      | (nr * nr) > n = True
      | otherwise = isPrime' n (nr + 1)

getFactors :: Int -> [Int]
getFactors n = getFactors' n 2
  where
    getFactors' :: Int -> Int -> [Int]
    getFactors' n f
      | n < f = []
      | (isPrime f) && ((modulo n f) == 0) = [f] ++ (getFactors' (quot n f) f)
      | otherwise = getFactors' n (f + 1)

collectSame :: [Int] -> [[Int]]
collectSame l 
  | last (collectSame' l) == emptyIntList = init (collectSame' l)
  | otherwise = collectSame' l
  where
    collectSame' :: [Int] -> [[Int]]
    collectSame' ([]) = [[]]
    collectSame' (n : []) = [[n]]
    collectSame' l = [fst spn] ++ collectSame' (snd spn)
      where 
        spn = span (eq) l 
        eq = (==) (head l)

getPows :: [Int] -> [Int]
getPows l = getPows' l 1
  where
    getPows' :: [Int] -> Int -> [Int]
    getPows' [] _ = []
    getPows' (n: []) i = [i * n]
    getPows' l i = [(head l) * i] ++ getPows' (tail l) (head l * i)

flattenList :: [[Int]] -> [Int]
flattenList (n:[]) = n
flattenList nrs = (head nrs) ++ flattenList (tail nrs)

-- getMapOfPows :: [Int] -> Map.Map Int Int
-- getMapOfPows l = Map.fromList l'
--   where 
--     l' = createList l
createList :: [Int] -> [(Int, Int)]
createList [] = [()]
createList (nr : []) = create nr
createList l = create (head l) ++ createList (tail l)
  where
    create :: Int -> [(Int, Int)]
    create nr = map (\ n -> (nr, n)) sortedPows
      where 
      sortedPows = sort(uniquePows)
      uniquePows = nub(flatPows)
      flatPows = flattenList(map getPows coll)
      pows = map getPows coll
      coll = collectSame factors
      factors = getFactors nr

-- gcdManyNumbersThree :: [Int] -> Int
-- gcdManyNumbersThree [] = 0
-- gcdManyNumbersThree nrs = mapOfPows
--   where 
--     mapOfPows = getMapOfPows nrs

  
