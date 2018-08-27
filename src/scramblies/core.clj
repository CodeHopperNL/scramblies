(ns scramblies.core
  (:require [clojure.set :as s]))

(defn- remove-matched-char
  "Removes a char that matched one in the needle from the candidates map."
  [candidates c]
  (case (get candidates c)
    1
    (dissoc candidates c)
    ;; else
    (update candidates c dec)))

(defn- add-unmatched-char
  "Adds a char that didn't match yet the needle from the candidates map."
  [candidates c]
  (update candidates c (fnil inc 0)))

(defn scramble?
  "Returns true if a portion of str1 characters can be rearranged to match str2, otherwise returns false."
  [str1 str2]
  (loop [haystack   str1
         needle     str2
         candidates {}]
    (if (empty? needle)
      true
      (let [[h & rest-haystack] haystack
            [n & rest-needle]   needle]
        (cond
          (nil? h)
          (if (contains? candidates n)
            (recur haystack rest-needle (remove-matched-char candidates n))
            false)

          (= h n)
          (recur rest-haystack rest-needle candidates)

          (contains? candidates n)
          (recur haystack rest-needle (remove-matched-char candidates n))

          :else
          (recur rest-haystack needle (add-unmatched-char candidates h)))))))
