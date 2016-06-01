# USE_ADSP for Audio DSP
USE_ADSP = "${@bb.utils.contains('DISTRO_FEATURES', 'adsp', '1', '0', d)}"
