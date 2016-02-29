/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/Condesa/Documents/Repositories/Androideity/AndroidStudioTutorials/SupportLibraryExample/customtabs/src/android/support/customtabs/ICustomTabsCallback.aidl
 */
package android.support.customtabs;
/**
 * Interface to a CustomTabsCallback.
 * @hide
 */
public interface ICustomTabsCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.support.customtabs.ICustomTabsCallback
{
private static final java.lang.String DESCRIPTOR = "android.support.customtabs.ICustomTabsCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.support.customtabs.ICustomTabsCallback interface,
 * generating a proxy if needed.
 */
public static android.support.customtabs.ICustomTabsCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.support.customtabs.ICustomTabsCallback))) {
return ((android.support.customtabs.ICustomTabsCallback)iin);
}
return new android.support.customtabs.ICustomTabsCallback.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_onNavigationEvent:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
android.os.Bundle _arg1;
if ((0!=data.readInt())) {
_arg1 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
this.onNavigationEvent(_arg0, _arg1);
return true;
}
case TRANSACTION_extraCallback:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.Bundle _arg1;
if ((0!=data.readInt())) {
_arg1 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
this.extraCallback(_arg0, _arg1);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements android.support.customtabs.ICustomTabsCallback
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void onNavigationEvent(int navigationEvent, android.os.Bundle extras) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(navigationEvent);
if ((extras!=null)) {
_data.writeInt(1);
extras.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onNavigationEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void extraCallback(java.lang.String callbackName, android.os.Bundle args) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(callbackName);
if ((args!=null)) {
_data.writeInt(1);
args.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_extraCallback, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_onNavigationEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_extraCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void onNavigationEvent(int navigationEvent, android.os.Bundle extras) throws android.os.RemoteException;
public void extraCallback(java.lang.String callbackName, android.os.Bundle args) throws android.os.RemoteException;
}
