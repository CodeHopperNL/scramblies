(ns scramblies.ui.http-test
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [scramblies.ui.http :as sut]
            [cljs-http.client :as http]
            [cljs.core.async :refer [onto-chan chan]]
            [cljs.test :refer-macros [deftest is testing]]))

(deftest http-scramble-processing
  (testing "Given a successful scramble response"
    (let [r {:status 200
             :body   {:scramblies.api/scramble? true}}]
      (is (= true (sut/scramble? r))
          "then the scramble is found")))
  (testing "Given a successful scramble response with a broken body"
    (let [r {:status 200
             :body 42}]
      (is (thrown? ExceptionInfo (sut/scramble? r))
          "then an exception is thrown")))
  (testing "Given a failing scramble response"
    (let [r {:status 200
             :body 42}]
      (is (thrown? ExceptionInfo (sut/scramble? r))
          "then an exception is thrown"))))
