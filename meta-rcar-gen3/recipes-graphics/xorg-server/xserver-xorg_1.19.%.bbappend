# Glamor for Xorg requires gbm >= 10.2.0.
# Glamor is not necessary with current env, so disable it.
PACKAGECONFIG_remove = "glamor"
