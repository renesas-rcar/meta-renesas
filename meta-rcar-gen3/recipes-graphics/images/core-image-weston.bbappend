require ${@"recipes-core/images/core-image-renesas-base.inc" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}
require ${@"core-image-renesas-mmp.inc" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}
