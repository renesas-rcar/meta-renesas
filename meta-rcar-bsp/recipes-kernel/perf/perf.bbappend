# arch/arm64/tools only exists since Linux 5.13 (needed by perf >= 6.7).
# linux-renesas 5.10 does not provide it, drop it to avoid the
# "Path does not exist" warning in do_configure.
PERF_SRC:remove = "arch/arm64/tools"
