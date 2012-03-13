(ns tools.utils
  (:require [clojure.string :as str]))

;; Print functions as #<fn> instead of the whole js code
(extend-type function
  IPrintable
  (-pr-seq [f opts] (list "#<fn>")))

(defn s
  "Convert objects to a readable string, separarated by a space."
  [& objs]
  (pr-str-with-opts objs (assoc (pr-opts) :readably false)))

(defn log [& objs]
  (.log js/console (apply s objs)))

(defn error [& objs]
  (throw (js/Error. (apply s objs))))

(defn stringify [x]
  (if (keyword? x)
    (name x)
    (str x)))

(defn id-string [args]
  (str/join "-" (map stringify args)))

(defn classes [& cls]
  (str/join " " (map stringify
                     (filter identity
                             cls))))

(defn type [x]
  (goog/typeOf x))

(defn boolean? [x]
  (or (= x true)
      (= x false)))
