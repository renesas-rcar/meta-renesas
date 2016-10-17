/*************************************************************************/ /*!
@File
@Copyright      Copyright (c) Imagination Technologies Ltd. All Rights Reserved
@License        Strictly Confidential.
*/ /**************************************************************************/

#if !defined(__WSEGL_H__)
#define __WSEGL_H__

#ifdef __cplusplus
extern "C" {
#endif

/*
// WSEGL Platform-specific definitions
*/
#if defined(__linux__)
#define WSEGL_EXPORT __attribute__((visibility("default")))
#define WSEGL_IMPORT
#else
#define WSEGL_EXPORT
#define WSEGL_IMPORT
#endif

/*
// WSEGL API Version Number
*/

#define WSEGL_VERSION 4
#define WSEGL_DEFAULT_DISPLAY 0
#define WSEGL_DEFAULT_NATIVE_ENGINE 0

#define WSEGL_FALSE		0
#define WSEGL_TRUE		1
#define WSEGL_NULL		0

#define	WSEGL_UNREFERENCED_PARAMETER(param) (param) = (param)

/*
// WSEGL handles
*/
typedef void *WSEGLDisplayHandle;
typedef void *WSEGLDrawableHandle;

/*
// Display capability type
*/
typedef enum WSEGLCapsType_TAG
{
	WSEGL_NO_CAPS = 0,
	WSEGL_CAP_MIN_SWAP_INTERVAL = 1, /* System default value = 1 */
	WSEGL_CAP_MAX_SWAP_INTERVAL = 2, /* System default value = 1 */
	WSEGL_CAP_WINDOWS_USE_HW_SYNC = 3, /* System default value = 0 (FALSE) */
	WSEGL_CAP_PIXMAPS_USE_HW_SYNC = 4, /* System default value = 0 (FALSE) */

	/* When this capability is set, the EGL lock is not taken around calls
	   to WSEGL functions. The WSEGL module is responsible for performing
	   its own locking in this case. */
	WSEGL_CAP_UNLOCKED = 5, /* System default value = 0 */

} WSEGLCapsType;

/*
// Display capability
*/
typedef struct WSEGLCaps_TAG
{
	WSEGLCapsType eCapsType;
	unsigned long ui32CapsValue;

} WSEGLCaps;

/*
// Drawable type
*/
#define WSEGL_NO_DRAWABLE			0x0
#define WSEGL_DRAWABLE_WINDOW		0x1
#define WSEGL_DRAWABLE_PIXMAP		0x2

/*
// YUV format flags and sync
*/
#define WSEGL_FLAGS_YUV_CONFORMANT_RANGE		(0 << 0)
#define WSEGL_FLAGS_YUV_FULL_RANGE				(1 << 0)
#define WSEGL_FLAGS_YUV_BT601					(0 << 1)
#define WSEGL_FLAGS_YUV_BT709					(1 << 1)
#define WSEGL_FLAGS_EGLIMAGE_COMPOSITION_SYNC	(1 << 2)

/*
// Maximum number of optional PowerVR Services 4 SYNCINFO objects
*/
#define WSEGL_MAX_SRC_SYNCS 32

/*
// Pixel format of display/drawable
*/
typedef enum WSEGLPixelFormat_TAG
{
	/* These must not be re-ordered */
	WSEGL_PIXELFORMAT_RGB565	= 0,
	WSEGL_PIXELFORMAT_ARGB4444	= 1,
	WSEGL_PIXELFORMAT_ARGB8888	= 2,
	WSEGL_PIXELFORMAT_ARGB1555	= 3,
	WSEGL_PIXELFORMAT_ABGR8888	= 4,
	WSEGL_PIXELFORMAT_XBGR8888	= 5,
	WSEGL_PIXELFORMAT_NV12		= 6,
	WSEGL_PIXELFORMAT_YUYV		= 7,
	WSEGL_PIXELFORMAT_YV12		= 8,
	WSEGL_PIXELFORMAT_XRGB8888	= 9,
	WSEGL_PIXELFORMAT_UYVY		= 10,
	WSEGL_PIXELFORMAT_NV12_4KALIGN = 11,
	WSEGL_PIXELFORMAT_NV21_4KALIGN = 12,
	WSEGL_PIXELFORMAT_R8		= 13,
	WSEGL_PIXELFORMAT_R8G8		= 14,
	WSEGL_PIXELFORMAT_NV21		= 15,
	WSEGL_PIXELFORMAT_I420		= 16,

	/* These are compatibility names only; new WSEGL
	 * modules should not use them.
	 */
	WSEGL_PIXELFORMAT_565		= WSEGL_PIXELFORMAT_RGB565,
	WSEGL_PIXELFORMAT_4444		= WSEGL_PIXELFORMAT_ARGB4444,
	WSEGL_PIXELFORMAT_8888		= WSEGL_PIXELFORMAT_ARGB8888,
	WSEGL_PIXELFORMAT_1555		= WSEGL_PIXELFORMAT_ARGB1555,
	WSEGL_PIXELFORMAT_YU12		= WSEGL_PIXELFORMAT_I420,

	/* Use BAD if something goes wrong during conversion */
	WSEGL_PIXELFORMAT_BAD		= 0xff,

} WSEGLPixelFormat;

/*
// Transparent of display/drawable
*/
typedef enum WSEGLTransparentType_TAG
{
	WSEGL_OPAQUE = 0,
	WSEGL_COLOR_KEY = 1,

} WSEGLTransparentType;

/*
// Display/drawable configuration
*/
typedef struct WSEGLConfig_TAG
{
	/*
	// Type of drawables this configuration applies to -
	// OR'd values of drawable types. 
	*/
	unsigned long			ui32DrawableType;

	/* Pixel format */
	WSEGLPixelFormat		ePixelFormat;

	/* Native Renderable  - set to WSEGL_TRUE if native renderable */
	unsigned long			ulNativeRenderable;

	/* FrameBuffer Level Parameter */
	unsigned long			ulFrameBufferLevel;

	/* Native Visual ID */
	unsigned long			ulNativeVisualID;

	/* Native Visual */
	unsigned long			ulNativeVisualType;

	/* Transparent Type */
	WSEGLTransparentType	eTransparentType;

	/* Transparent Color - only used if transparent type is COLOR_KEY */
	unsigned long			ulTransparentColor; /* packed as 0x00RRGGBB */

	/* Framebuffer Target - set to WSEGL_TRUE if config compatible with framebuffer */
	unsigned long			ulFramebufferTarget;

} WSEGLConfig;

/*
// WSEGL errors
*/
typedef enum WSEGLError_TAG
{
	WSEGL_SUCCESS = 0,
	WSEGL_CANNOT_INITIALISE = 1,
	WSEGL_BAD_NATIVE_DISPLAY = 2,
	WSEGL_BAD_NATIVE_WINDOW = 3,
	WSEGL_BAD_NATIVE_PIXMAP = 4,
	WSEGL_BAD_NATIVE_ENGINE = 5,
	WSEGL_BAD_DRAWABLE = 6,
	WSEGL_BAD_MATCH = 7,
	WSEGL_OUT_OF_MEMORY = 8,
	WSEGL_RETRY = 9,

	/* These are compatibility names only; new WSEGL
	 * modules should not use them.
	 */
	WSEGL_BAD_CONFIG = WSEGL_BAD_MATCH,

} WSEGLError; 

/*
// Drawable orientation (in degrees anti-clockwise)
*/
typedef enum WSEGLRotationAngle_TAG
{
	WSEGL_ROTATE_0 = 0,
	WSEGL_ROTATE_90 = 1,
	WSEGL_ROTATE_180 = 2,
	WSEGL_ROTATE_270 = 3,

	/* Use BAD if something goes wrong during conversion */
	WSEGL_ROTATE_BAD = 0xff,

} WSEGLRotationAngle; 

/*
// Drawable information required by OpenGL-ES driver
*/
typedef struct WSEGLDrawableParams_TAG
{
	/* Width in pixels of the drawable */
	unsigned long			ui32Width;

	/* Height in pixels of the drawable */
	unsigned long			ui32Height;

	/* Stride in pixels of the drawable */
	unsigned long			ui32Stride;

	/* Pixel format of the drawable */
	WSEGLPixelFormat		ePixelFormat;

	/* User space cpu virtual address of the drawable */
	void   					*pvLinearAddress;

	/* HW address of the drawable */
	unsigned long			ui32HWAddress;

	/* Override display's HW_SYNC mode */
	unsigned long			bWaitForRender;

	/* Flags */
	unsigned long			ulFlags;

	/* Rotation angle of drawable (presently source only) */
	WSEGLRotationAngle		eRotationAngle;

	/*
	// Optional PowerVR Services 4 MEMINFO pointer. This may be used for
	// internal (implicit) synchronization purposes, and by PDUMP. It should
	// refer to the same object as the other fields in this structure.
	*/
	void					*hMemInfo;

	/*
	// Optional PowerVR Services 4 SYNCINFO pointers to sent down as source
	// surface (texture) dependencies of a render. If these are provided
	// when not applicable, they will be ignored. If a sync is not needed,
	// it should be passed as NULL.
	*/
	void					*ahSyncInfo[WSEGL_MAX_SRC_SYNCS];

} WSEGLDrawableParams;


/*
// Table of function pointers that is returned by WSEGL_GetFunctionTablePointer()
//
// The first entry in the table is the version number of the wsegl.h header file that
// the module has been written against, and should therefore be set to WSEGL_VERSION
*/
typedef struct WSEGL_FunctionTable_TAG
{
	unsigned long ui32WSEGLVersion;

	WSEGLError (*pfnWSEGL_IsDisplayValid)(NativeDisplayType);

	WSEGLError (*pfnWSEGL_InitialiseDisplay)(NativeDisplayType, WSEGLDisplayHandle *, const WSEGLCaps **, WSEGLConfig **);

	WSEGLError (*pfnWSEGL_CloseDisplay)(WSEGLDisplayHandle);

	WSEGLError (*pfnWSEGL_CreateWindowDrawable)(WSEGLDisplayHandle, WSEGLConfig *, WSEGLDrawableHandle *, NativeWindowType, WSEGLRotationAngle *);

	WSEGLError (*pfnWSEGL_CreatePixmapDrawable)(WSEGLDisplayHandle, WSEGLConfig *, WSEGLDrawableHandle *, NativePixmapType, WSEGLRotationAngle *);

	WSEGLError (*pfnWSEGL_DeleteDrawable)(WSEGLDrawableHandle);

	WSEGLError (*pfnWSEGL_SwapDrawable)(WSEGLDrawableHandle, unsigned long);

	WSEGLError (*pfnWSEGL_SwapControlInterval)(WSEGLDrawableHandle, unsigned long);

	WSEGLError (*pfnWSEGL_WaitNative)(WSEGLDrawableHandle, unsigned long);

	WSEGLError (*pfnWSEGL_CopyFromDrawable)(WSEGLDrawableHandle, NativePixmapType);

	WSEGLError (*pfnWSEGL_CopyFromPBuffer)(void *, unsigned long, unsigned long, unsigned long, WSEGLPixelFormat, NativePixmapType);

	WSEGLError (*pfnWSEGL_GetDrawableParameters)(WSEGLDrawableHandle, WSEGLDrawableParams *, WSEGLDrawableParams *, unsigned long);

	WSEGLError (*pfnWSEGL_ConnectDrawable)(WSEGLDrawableHandle);

	WSEGLError (*pfnWSEGL_DisconnectDrawable)(WSEGLDrawableHandle);

	WSEGLError (*pfnWSEGL_FlagStartFrame)(WSEGLDrawableHandle);


#if defined (__QNXNTO__)
	WSEGLError (*pfnWSEGL_WaitForDrawableRenderBuffer)(WSEGLDrawableHandle);
#endif

} WSEGL_FunctionTable;


WSEGL_IMPORT const WSEGL_FunctionTable *WSEGL_GetFunctionTablePointer(void);

typedef struct EGLNativePixmapTypeREL_TAG
{
	EGLint	width;
	EGLint	height;
	EGLint	stride;
	EGLint	usage;
	EGLint	format;
	void*	pixelData;
} EGLNativePixmapTypeREL;

#define EGL_NATIVE_PIXFORMAT_RGB565_REL			0
#define EGL_NATIVE_PIXFORMAT_ARGB1555_REL		1
#define EGL_NATIVE_PIXFORMAT_ARGB8888_REL		2
#define EGL_NATIVE_PIXFORMAT_ARGB4444_REL		3

#ifdef __cplusplus
}
#endif 

#endif /* __WSEGL_H__ */

/******************************************************************************
 End of file (wsegl.h)
******************************************************************************/
