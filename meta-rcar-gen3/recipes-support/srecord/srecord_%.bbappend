# srecod-native is build inside uninative environment
# so it depends on libraries that maybe not present on
# host during runtime.
# For example, uninative contains libc 2.3.6, but host provides
# only libc 2.3.1.
# As result srec_cat is looking for functions not available on host.
#
# So we use rpath to use same libraries that were used for build.
BUILD_LDFLAGS_append = " -Wl,-rpath=${STAGING_DIR}-uninative/${BUILD_ARCH}-linux/lib"

