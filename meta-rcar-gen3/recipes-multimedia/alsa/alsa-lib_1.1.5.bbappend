FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Apply pcm fix patch
SRC_URI_append = " \
    file://0001-pcm-Return-the-consistent-error-code-for-unexpected-.patch \
"
