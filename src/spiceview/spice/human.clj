(ns spiceview.spice.human)

(def filetypes
  {"lbl" "Label?"
   "bc"  "binary CK file"
   "bdb" "binary database kernel"
   "bds" "binary DSK file"
   "bep" "binary Science Plan EK file"
   "bes" "binary Sequence Component EK file"
   "bpc" "binary PcK file"
   "bsp" "binary SPK file"
   "ten" "text Experimenter’s Notebook EK file"
   "tf"  "text FK file"
   "ti"  "text IK file"
   "tls" "text LSK file"
   "tm"  "text meta-kernel file (“FURNSH kernel”)"
   "tpc" "text PcK file"
   "tsc" "text SCLK file"
   "xc"  "transfer format CK file"
   "xdb" "transfer format database kernel"
   "xep" "transfer format Science Plan EK file"
   "xes" "transfer format Sequence Component EK file"
   "xpc" "transfer format PcK file"
   "xsp" "transfer format SPK file"})

(def dirtypes
  {"ck"   "Orientation"
   "dsk"  "Digital shape models"
   "enb"  "Experimenter’s Notebook"
   "esp"  "Science Plan"
   "esq"  "Sequence of events"
   "fk"   "Frame Kernel"
   "ik"   "instrument kernel"
   "lsk"  "leap seconds"
   "pck"  "Planetary Constants"
   "sclk" "Spacecraft clock"
   "spk"  "Spacecraft & Planet log"})
