(ns scramblies.ui.test-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [scramblies.ui.http-test]))

(doo-tests 'scramblies.ui.http-test)
