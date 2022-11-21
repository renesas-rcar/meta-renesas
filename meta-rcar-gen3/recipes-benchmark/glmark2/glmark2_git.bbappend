FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

require ${@"glmark2.inc" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}

