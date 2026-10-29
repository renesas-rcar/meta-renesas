FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
    file://0001-Fix-1558-Don-t-assume-inclusion-of-cstdint.patch \
    file://0001-libdnf-conf-OptionNumber.hpp-add-missing-cstdint-inc.patch \
    file://0001-libdnf-utils-sqlite3-Sqlite3.hpp-add-missing-cstdint.patch \
    "
