PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('x11', '')}"
PACKAGECONFIG = "fbdev"

EXTRA_OECONF += "\
	--disable-xwayland-test \
	WESTON_NATIVE_BACKEND=fbdev-backend.so \
"
