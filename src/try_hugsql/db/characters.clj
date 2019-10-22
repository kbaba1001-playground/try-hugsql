(ns try-hugsql.db.characters
  (:require [hugsql.core :as hugsql]))

; TODO jdbc で postgres に繋げられるようにする
(def db
  {:dbtype "postgres"
   :dbname "sample-dev"
   :port "5439"
   :user "postgres"
   :password "postgres"})

;; The path is relative to the classpath (not proj dir!),
;; so "src" is not included in the path.
;; The same would apply if the sql was under "resources/..."
;; Also, notice the under_scored path compliant with
;; Clojure file paths for hyphenated namespaces
(hugsql/def-db-fns "try_hugsql/db/sql/characters.sql")

;; For most HugSQL usage, you will not need the sqlvec functions.
;; However, sqlvec versions are useful during development and
;; for advanced usage with database functions.
(hugsql/def-sqlvec-fns "try_hugsql/db/sql/characters.sql")

; (characters-by-ids-specify-cols-sqlvec {:ids [1 2], :cols ["name" "specialty"]})  

; ;;=>
; ["select name, specialty from characters
;   where id in (?,?)"
;  1
;  2]

; (characters/characters-by-ids-specify-cols db
;                                            {:ids [1 2], :cols ["name" "specialty"]})  ;;=>
; ({:name "Westley", :specialty "love"}
;  {:name "Buttercup", :specialty "beauty"})
