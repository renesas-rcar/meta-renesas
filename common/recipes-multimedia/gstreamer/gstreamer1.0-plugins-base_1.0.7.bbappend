PRINC := "${@int(PRINC) + 1}"
  
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://0001-videodecoder-promote-the-access-level-of-frame-relea.patch"
 
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('x11', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('ivorbis', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('ogg', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('theora', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('vorbis', '')}"

EXTRA_OECONF += "--disable-nls"
