(ns clj-noob.core)

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])
(defn needs-matching-part?
  [part]
  (re-find #"^left-" (:name part)))

(defn make-matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps which have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (let [final-body-parts (conj final-body-parts part)]
              (if (needs-matching-part? part)
                (conj final-body-parts (make-matching-part part))
                final-body-parts)))
          []
          asym-body-parts))

(def sym-parts
  (symmetrize-body-parts asym-hobbit-body-parts))

(defn get-last
  [coll]
  (take-last 1 coll))

(defn get-nth
  [coll index]
  (first (drop index coll)))

(defn count-seq
  [coll]
  (loop [coll coll x 0]
    (if (empty? coll)
      x
      (recur (rest coll) (inc x)))))

(defn better-count
  [coll]
  (reduce + (map (constantly 1) coll)))

(defn rev-seq
  [coll]
  (loop [c coll acc '()]
    (if (empty? c)
      acc
      (recur (rest c) (cons (first c) acc)))))

(defn rev-seq-2000
  [coll]
  (reduce #(cons %2 %1) '() coll))

(defn fib
  [n]
  (loop [coll '(1 1) n (- n 2)]
    (if (= n 0)
      (reverse coll)
      (recur  (cons (+ (first coll) (second coll)) coll) (dec n)))))

(defn better-fib
  [n]
  (take n (map first (iterate (fn [[a b]] [b (+ a b)]) [1 1]))))

(defn palin
  [coll]
  (let [func (comp apply str)]
    (= (func coll) (func (reverse coll)))))

(defn my-flat
  [coll]
  (loop [coll coll acc '()]
    (if (empty? coll)
        acc
        (if (seq? (first coll))
          (recur (cons (first coll) ) acc)
          (recur (rest coll) (cons (first coll) acc))))))

(def bk-seq [1 2])
(first bk-seq)
(def bk-func (fn [x] + x x))
(bk-func 1)
(prn (+ 1 bk-seq))
