# USE_ADSP for Audio DSP
USE_ADSP = "${@base_contains('DISTRO_FEATURES', 'adsp', '1', '0', d)}"
