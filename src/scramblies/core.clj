(ns scramblies.core
  (:require [clojure.set :as s]))

(defn- remove-matched-char
  "Removes a char that matched one in the needle from the previous map."
  [previous c]
  (case (get previous c)
    1
    (dissoc previous c)
    ;; else
    (update previous c dec)))

(defn- add-unmatched-char
  "Adds a char that didn't match yet the needle from the previous map."
  [previous c]
  (update previous c (fnil inc 0)))

(defn scramble?
  "Returns true if a portion of str1 characters can be rearranged to match str2, otherwise returns false."
  [str1 str2]
  (loop [haystack str1
         needle   str2
         previous {}]
    (let [[h & rest-haystack] haystack
          [n & rest-needle]   needle]
      (cond
        (empty? needle) ;; -> matched all chars, we're done
        true

        (nil? h) ;; -> we ran through the whole haystack
        (if (contains? previous n)
          (recur haystack rest-needle (remove-matched-char previous n))
          false)

        (= h n) ;; -> char-by-char match
        (recur rest-haystack rest-needle previous)

        (contains? previous n) ;; -> previous char match
        (recur haystack rest-needle (remove-matched-char previous n))

        :else ;; -> no match so far, moving along
        (recur rest-haystack needle (add-unmatched-char previous h))))))
