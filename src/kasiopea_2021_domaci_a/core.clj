(ns kasiopea-2021-domaci-a.core
  (:require [clojure.string :as str]))

(def coins-vals [1 2 5 10 20 50])

(defn read-input []
  (->> "input.txt" slurp
       str/split-lines
       rest
       (partition 2)
       (map (fn [[cost coins]]
              (let [split-coins (->> (str/split coins #"\ ")
                                     (map read-string))]
                [(read-string cost) split-coins])))))

(defn -main [& _args]
  (let [input (read-input)
        calculated (map (fn [[cost coins]]
                          (let [total-coins (apply + (map * coins coins-vals))]
                            (if (>= total-coins cost)
                              "ANO"
                              "NE")))
                        input)]
    (->> calculated (str/join "\n")
         (spit "output.txt"))))