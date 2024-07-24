require ${@"core-image-renesas-base.inc" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}
require ${@"core-image-weston.inc" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}
