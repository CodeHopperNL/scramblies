(ns scramblies.core
  (:require [clojure.spec.alpha :as s]))

(s/def ::pos? (s/and number? pos?))
(s/def ::previous-chars (s/map-of char? ::pos?))

(defn- remove-matched-char
  "Removes a char that matched one in the needle from the previous map."
  [previous c]
  (case (get previous c)
    1
    (dissoc previous c)

    nil
    (throw (ex-info "Cannot flag as a match a character which was not seen before" {:previous previous
                                                                                    :invalid-char c}))

    ;; else
    (update previous c dec)))

(s/fdef remove-matched-char
  :args (s/cat :previous ::previous-chars
               :char char?)
  :ret  ::previous-chars
  :fn   #(let [count-chars (fn [m] (apply + (vals m)))]
           (= (count-chars (:ret %))
              (dec (count-chars (-> % :args :previous))))))

(defn- add-unmatched-char
  "Adds a char that didn't match yet the needle from the previous map."
  [previous c]
  (update previous c (fnil inc 0)))

(s/fdef add-unmatched-char
  :args (s/cat :previous ::previous-chars
               :char char?)
  :ret  ::previous-chars
  :fn   #(let [count-chars (fn [m] (apply + (vals m)))]
           (= (count-chars (:ret %))
              (inc (count-chars (-> % :args :previous))))))

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

(s/fdef scramble?
  :args (s/cat :str1 (s/nilable string?)
               :str2 (s/nilable string?))
  :ret  boolean?
  :fn   #(let [freq1 (frequencies (-> % :args :str1))
               freq2 (frequencies (-> % :args :str2))
               ;; a more idiomatic but less performant version of scramble?:
               s?    (every? (fn [[k v]] (<= v (get freq1 k -1))) freq2)]
           (= s? (-> % :ret))))
