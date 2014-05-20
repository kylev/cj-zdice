(ns cj-zdice.game)

(def dice-kinds [{:kind :green,  :sides (concat (repeat 3 :brain) (repeat 2 :runner) (repeat 1 :bang))}
                 {:kind :yellow, :sides (concat (repeat 2 :brain) (repeat 2 :runner) (repeat 2 :bang))}
                 {:kind :red,    :sides (concat (repeat 1 :brain) (repeat 2 :runner) (repeat 3 :bang))}])

(defn dice-of
  "Return a die of the desired type."
  [desired]
  (first (filter #(= desired (% :kind)) dice-kinds)))

(defn dice-cup [] (concat (repeat 6 (dice-of :green)) (repeat 4 (dice-of :yellow)) (repeat 3 (dice-of :red))))

;; (dice-cup)

(defn dice-roll
  "Roll the list of dice and return their visible variants."
  [dice]
  (map #(hash-map :kind (% :kind) :showing (rand-nth (% :sides))) dice))

(defn new
  "Create a new game with initial state."
  []
  {:turn 0
   :players []
   :cup (dice-cup)})
