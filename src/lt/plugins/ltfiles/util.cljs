(ns lt.plugins.ltfiles.util
  (:require [lt.objs.editor :as editor]
            [lt.objs.editor.pool :as pool]))

(defn current-word*
  "Returns current word given string and cursor position in string"
  [string cursor]
  (str
   (re-find #"\S+$" (subs string 0 cursor))
   (re-find #"\S+" (subs string cursor))))

(defn current-word
  "Current word under cursor"
  []
  (when-let [ed (pool/last-active)]
    (let [cursor (editor/->cursor ed)]
      (current-word* (editor/line ed (:line cursor))
                    (:ch cursor)))))

(defn parent? [parent-path path]
  (re-find (re-pattern (str "^" parent-path)) path))

(defn current-file []
  (-> @(pool/last-active) :info :path))
