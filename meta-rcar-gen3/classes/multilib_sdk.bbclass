# This bbclass removes the lib32 packages when populate_sdk for core-image-*,
# To create SDK for 32bit libraries, target image must be lib32-core-image-*
python __anonymous () {
    pn = d.getVar('PN', True)
    if pn.startswith('lib32-'):
        return

    target_arch = d.getVar('TARGET_ARCH', True)
    tc_target_task = d.getVar('TOOLCHAIN_TARGET_TASK', True)
    revise_tc_target_task = ''
    if target_arch == "aarch64":
        list = []
        for pkgs in tc_target_task.split():
            if not pkgs.startswith('lib32-'):
                list.append(pkgs)
    d.setVar('TOOLCHAIN_TARGET_TASK', ' '.join(list))
}

MULTILIBRE_ALLOW_REP =. "${includedir}|"
