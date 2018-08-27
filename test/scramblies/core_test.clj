(ns scramblies.core-test
  (:require [clojure.test :refer :all]
            [scramblies.core :refer :all]
            [orchestra.spec.test :as st]))

(st/instrument)

(deftest scrambles-test
  (testing "real scrambles are detected"
    (are [val1 val2] (scramble? val1 val2)
      "rekqodlw" "world"
      "cedewaraaossoqqyt" "codewars"))
  (testing "can detect the lack of scrambling match"
    (are [val1 val2] (not (scramble? val1 val2))
      "and" "aaand"
      "katas" "steak")))
