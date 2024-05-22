This is a Yocto/OpenEmbedded layer for Servo browser.

Maintainer: Gyorgy Sarvari/skandigraun@gmail.com

This layer depends on meta-clang, poky and meta-oe layers. At this time only Scarthgap is supported.

Important note: the current version of Servo must be compiled with Rust 1.74. Using 1.75 it fails to compile, and 1.73 is untested (Servo itself downloads 1.74 also, when built the official way). 

Unfortunately the required version of Rust is not provided by either poky nor meta-rust, so it is shipped with this layer. This comes with one or two caveats:

- If you are using Rust for other recipes also in your image, then you have to use the shipped version for that too. (Make sure to set the appropriate `PREFERRED_VERSION`/`REQUIRED_VERSION` variables).
- Unfortunately I am not able to provide Rust support beyond what is required to compile Servo, mostly because I am not that familiar with the depths of the Rust compiler. I am however happy to get PRs to fix things.

Once Servo uses some Rust version that is officially supported by OE, I will remove this shipped version.

This version of Servo layer has been tested only on one architecture so far, on aarch64 - PinePhonePro. It does work there. In theory it should compile to arm and x86_64 also, but I can test this only in the future.

In my understanding RISC-V is unsupported by Servo at this time, though this is also unverified for now.

Servo is a very quickly changing project, realistically it can't be tracked real time. I intend to create a new release every 7-10 days, with the latest commit from that point of time.

At this time only the browser itself is available in the layer.

Feel free to open an issue or PR, if you see it fit.

# Errors

```
Failed to create WR surfman: ContextCreationFailed(BadMatch) (thread main, at ports/servoshell/headed_window.rs:136)
Redirecting call to abort() to mozalloc_abort

Caught signal 11 in thread "main"
   0: <servoshell::backtrace::Print as core::fmt::Debug>::fmt
Segmentation fault (core dumped)
```

If Servo crashes right away upon starting with the above error, that seems to refer to some GPU driver error (under investigation, what).
 
It can be circumvented by starting Servo like `LIBGL_ALWAYS_SOFTWARE=1 ./servo`. Performance will likely suffer, though.

# Source of magic commits

This supposed to be just a reminder for me, where are the magic commits in the recipe coming from.

| Resource | Commit | Source of commit |
| -------- | ------ | ---------------- |
| cssparser | aaa966d9d6ae70c4b8a62bb5e3a14c068bb7dff0 | Referenced by top level Cargo.toml |
| stylo | ac65c5a27c1b9faf9e7bb5bbcb3a4837a810ef6b | This is the `HEAD` of `2024-04-16` branch (at the time of writing this), which is referenced by the top level Cargo.toml |
| media | 45756bef67037ade0f4f0125d579fdc3f3d457c8 | This is the `HEAD` of `main` branch (at this time), which is referenced by the top level Cargo.toml |
| webrender | 9d354adf8955b1390dd56db89e6d5a9ea7880391 | This is the `HEAD` of `0.64` branch currently, which is referenced by top level Cargo.toml |
| wgpu | d0a5e48aa7e84683114c3870051cc414ae92ac03 | Commit referenced by top level Cargo.toml |
| webxr | 88fd368d1cc110db0f6f5000de2e22a14c9423b5 | This is the `HEAD` of `main` branch, which is referenced by a number of Cargo.toml files, e.g ./components/canvas/Cargo.toml or ./components/script/Cargo.toml |
| fontsan | 8fbc406506cfd1f8ab60e625d1e926a0e72e1d8a | This is the `HEAD` of `main` branch, which is referenced by ./components/gfx/Cargo.toml |
| signpost | 7ed712507f343c38646b9d1fefd049166f9c9a18 | This is the `HEAD` of `master` branch, which is referenced by ./components/shared/profile/Cargo.toml |
| mozjs | 89121ff072638faee148e418a3eb2c3a2229fde5 | This is the `HEAD` of `main` branch, which is referenced by ./components/script/Cargo.toml |
