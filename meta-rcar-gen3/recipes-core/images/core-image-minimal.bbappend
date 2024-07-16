require ${@bb.utils.contains('MACHINE_FEATURES', 'upstream-kernel', 'core-image-renesas-minimal.inc', 'core-image-renesas-base.inc', d)}
