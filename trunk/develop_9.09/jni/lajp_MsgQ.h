/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class lajp_MsgQ */

#ifndef _Included_lajp_MsgQ
#define _Included_lajp_MsgQ
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     lajp_MsgQ
 * Method:    msgget
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_lajp_MsgQ_msgget
  (JNIEnv *, jclass, jint);

/*
 * Class:     lajp_MsgQ
 * Method:    msgsnd
 * Signature: (II[BI)I
 */
JNIEXPORT jint JNICALL Java_lajp_MsgQ_msgsnd
  (JNIEnv *, jclass, jint, jint, jbyteArray, jint);

/*
 * Class:     lajp_MsgQ
 * Method:    msgrcv
 * Signature: (I[BII)I
 */
JNIEXPORT jint JNICALL Java_lajp_MsgQ_msgrcv
  (JNIEnv *, jclass, jint, jbyteArray, jint, jint);

/*
 * Class:     lajp_MsgQ
 * Method:    msgrcvNoBlock
 * Signature: (I[BII)I
 */
JNIEXPORT jint JNICALL Java_lajp_MsgQ_msgrcvNoBlock
  (JNIEnv *, jclass, jint, jbyteArray, jint, jint);

/*
 * Class:     lajp_MsgQ
 * Method:    msgclose
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_lajp_MsgQ_msgclose
  (JNIEnv *, jclass, jint);

/*
 * Class:     lajp_MsgQ
 * Method:    shmget
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_lajp_MsgQ_shmget
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     lajp_MsgQ
 * Method:    shmclose
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_lajp_MsgQ_shmclose
  (JNIEnv *, jclass, jint);

/*
 * Class:     lajp_MsgQ
 * Method:    semget
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_lajp_MsgQ_semget
  (JNIEnv *, jclass, jint);

/*
 * Class:     lajp_MsgQ
 * Method:    semclose
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_lajp_MsgQ_semclose
  (JNIEnv *, jclass, jint);

#ifdef __cplusplus
}
#endif
#endif
