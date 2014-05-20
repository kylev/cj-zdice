(ns cj-zdice.game)

(defn gen-die
  "Generate a sequence of sides composed of (brain, runner, bang) sides."
  [brain runner bang]
  (vec (concat (repeat brain :brain) (repeat runner :runner) (repeat bang :bang))))

(def die-types {:red (gen-die 1 2 3)
                :yellow (gen-die 2 2 2)
                :green (gen-die 3 2 1)})

(defn dice-of
  "Return die of the desired type."
  [desired]
  {:kind desired :sides (desired die-types)})

;; (dice-of :red)

(defn dice-cup
  "Generate a standard starting cup of dice."
  []
  (concat (repeat 6 (dice-of :green)) (repeat 4 (dice-of :yellow)) (repeat 3 (dice-of :red))))

(defn die-roll
  "Roll a die, adding a :showing side."
  [die]
  (let [sides (:sides die)]
    (assoc die :showing (nth sides (rand-int (count sides))))))

;; (die-roll (dice-of :red))

(defn new
  "Create a new game with initial state."
  []
  {:turn 0
   :players []
   :cup (dice-cup)})
