## Fix warning for building with core-image-weston-sdk.
## System may inform some user/group already existed,
## Groupadd/useradd will not be executed.
## This is to fix those warning.
perform_groupadd_prepend () {
    local rootdir_pre="$1"
    local opts_pre="$2"
    local groupname_pre=`echo "$opts_pre" | awk '{ print $NF }'`
    local group_exists_pre="`grep "^$groupname_pre:" $rootdir_pre/etc/group || true`"
    if test "x$group_exists_pre" != "x"; then
        exit
    fi
}

perform_useradd_prepend () {
    local rootdir_pre="$1"
    local opts_pre="$2"
    local username_pre=`echo "$opts_pre" | awk '{ print $NF }'`
    local user_exists_pre="`grep "^$username_pre:" $rootdir_pre/etc/passwd || true`"
    if test "x$user_exists_pre" != "x"; then
        exit
    fi
}
