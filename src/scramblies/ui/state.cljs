(ns scramblies.ui.state
  "Frontend state management"
  (:require [reagent.core :as r]))

(def state (r/atom {}))

(defn lock!
  "Marks the app as locked to stop accepting user input during HTTP interactions."
  []
  (swap! state assoc ::locked true))

(defn unlock!
  "Marks the app as unlocked to start again accepting user input"
  []
  (swap! state dissoc ::locked))

(defn locked?
  "Checks whether the app state is marked as locked"
  [state]
  (true? (::locked state)))
