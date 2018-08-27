(ns scramblies.core-test
  (:require [clojure.test :refer :all]
            [scramblies.core :refer :all]))

(defn gotta-test-em-all
  [pred]
  (are [expected val1 val2] (= expected (pred val1 val2))
    true
    "rekqodlw" "world"

    true
    "cedewaraaossoqqyt" "codewars"

    false
    "and" "aaand"

    false
    "katas" "steak"))

(deftest scramblies-test
  (testing "Faster version"
    (gotta-test-em-all scramble?)))
