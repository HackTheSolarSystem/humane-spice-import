(ns spiceview.browser.collation-test
(:require [spiceview.browser.collation :as sut :refer [parse-filename parse-filenames]]
          [clojure.test :refer :all]
          ;;[midje.sweet :refer :all]
))

(deftest
  ^:test-refresh/focus
  parse-test
  (testing "It can parse homogenous filenames"

    (is (= (parse-filenames ["juno_jade_v00.ti"
                             "juno_jade_v01.ti"
                             "juno_jedi_v00.ti"
                             "juno_jedi_v01.ti"]) {"ti" {"juno" {"jade" ["v00"
                                                                         "v01"]
                                                                 "jedi" ["v00"
                                                                         "v01"]}}}))

    (is (= (parse-filenames ["JNO_SCLKSCET.00000.tsc"
                             "JNO_SCLKSCET.00001.tsc"
                             "JNO_SCLKSCET.00002.tsc"]) {"tsc" { "JNO_SCLKSCET" ["00000"
                                                                                 "00001"
                                                                                 "00002"]}}))


    (is (= (parse-filenames ["juno_v01.tf"
                             "juno_v02.tf"]) {"tf" {"juno_" ["v01"
                                                             "v02"]}}))

    (is (= (parse-filenames ["naif0009.tls"
                             "naif0010.tls"
                             "naif0011.tls"
                             "naif0012.tls"]) {"tls" {"naif" ["0009"
                                                              "0010"
                                                              "0011"
                                                              "0012"]}}))

    (is (= (parse-filenames ["pck00009.tpc"
                             "pck00010.tpc"
                             "pck00011.tpc"]) {"tpc" {"pck" ["00009"
                                                             "00010"
                                                             "00011"]}}))

    )

  (testing "It can parse heterogenous filenames"
    ;; (is (= (parse-filename "de421.bsp") "expected-result"))
    ;; (is (= (parse-filename "de421.bsp.lbl") "expected-result"))
    ;; (is (= (parse-filename "juno_pred_orbit.bsp") "expected-result"))
    ;; (is (= (parse-filename "juno_sc_nom_110807_171016_v01.cmt") "expected-result"))
    ;; (is (= (parse-filename "juno_sc_prl_130118_130215_jc020a01_v01.bc") "expected-result"))
    ;; (is (= (parse-filename "juno_sc_rec_140427_140503_v03.bc") "expected-result"))

    ;; (is (= (parse-filename "spk_merge_110805_171017_130515.bsp") "expected-result"))

    )
  )
