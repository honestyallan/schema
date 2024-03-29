/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.harvest.demo.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2015-12-29")
public class IGroupMemberQueryService {

  public interface Iface {

    public GroupMemberListResult getAllGroupMembers(long arg0, long arg1) throws org.apache.thrift.TException;

  }

  public interface AsyncIface {

    public void getAllGroupMembers(long arg0, long arg1, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException;

  }

  public static class Client extends org.apache.thrift.TServiceClient implements Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public GroupMemberListResult getAllGroupMembers(long arg0, long arg1) throws org.apache.thrift.TException
    {
      send_getAllGroupMembers(arg0, arg1);
      return recv_getAllGroupMembers();
    }

    public void send_getAllGroupMembers(long arg0, long arg1) throws org.apache.thrift.TException
    {
      getAllGroupMembers_args args = new getAllGroupMembers_args();
      args.setArg0(arg0);
      args.setArg1(arg1);
      sendBase("getAllGroupMembers", args);
    }

    public GroupMemberListResult recv_getAllGroupMembers() throws org.apache.thrift.TException
    {
      getAllGroupMembers_result result = new getAllGroupMembers_result();
      receiveBase(result, "getAllGroupMembers");
      if (result.isSetSuccess()) {
        return result.success;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "getAllGroupMembers failed: unknown result");
    }

  }
  public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void getAllGroupMembers(long arg0, long arg1, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException {
      checkReady();
      getAllGroupMembers_call method_call = new getAllGroupMembers_call(arg0, arg1, resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class getAllGroupMembers_call extends org.apache.thrift.async.TAsyncMethodCall {
      private long arg0;
      private long arg1;
      public getAllGroupMembers_call(long arg0, long arg1, org.apache.thrift.async.AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.arg0 = arg0;
        this.arg1 = arg1;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("getAllGroupMembers", org.apache.thrift.protocol.TMessageType.CALL, 0));
        getAllGroupMembers_args args = new getAllGroupMembers_args();
        args.setArg0(arg0);
        args.setArg1(arg1);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public GroupMemberListResult getResult() throws org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_getAllGroupMembers();
      }
    }

  }

  public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("getAllGroupMembers", new getAllGroupMembers());
      return processMap;
    }

    public static class getAllGroupMembers<I extends Iface> extends org.apache.thrift.ProcessFunction<I, getAllGroupMembers_args> {
      public getAllGroupMembers() {
        super("getAllGroupMembers");
      }

      public getAllGroupMembers_args getEmptyArgsInstance() {
        return new getAllGroupMembers_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public getAllGroupMembers_result getResult(I iface, getAllGroupMembers_args args) throws org.apache.thrift.TException {
        getAllGroupMembers_result result = new getAllGroupMembers_result();
        result.success = iface.getAllGroupMembers(args.arg0, args.arg1);
        return result;
      }
    }

  }

  public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProcessor.class.getName());
    public AsyncProcessor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>>()));
    }

    protected AsyncProcessor(I iface, Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends AsyncIface> Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      processMap.put("getAllGroupMembers", new getAllGroupMembers());
      return processMap;
    }

    public static class getAllGroupMembers<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, getAllGroupMembers_args, GroupMemberListResult> {
      public getAllGroupMembers() {
        super("getAllGroupMembers");
      }

      public getAllGroupMembers_args getEmptyArgsInstance() {
        return new getAllGroupMembers_args();
      }

      public AsyncMethodCallback<GroupMemberListResult> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
        final org.apache.thrift.AsyncProcessFunction fcall = this;
        return new AsyncMethodCallback<GroupMemberListResult>() { 
          public void onComplete(GroupMemberListResult o) {
            getAllGroupMembers_result result = new getAllGroupMembers_result();
            result.success = o;
            try {
              fcall.sendResponse(fb,result, org.apache.thrift.protocol.TMessageType.REPLY,seqid);
              return;
            } catch (Exception e) {
              LOGGER.error("Exception writing to internal frame buffer", e);
            }
            fb.close();
          }
          public void onError(Exception e) {
            byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
            org.apache.thrift.TBase msg;
            getAllGroupMembers_result result = new getAllGroupMembers_result();
            {
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = (org.apache.thrift.TBase)new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
            }
            try {
              fcall.sendResponse(fb,msg,msgType,seqid);
              return;
            } catch (Exception ex) {
              LOGGER.error("Exception writing to internal frame buffer", ex);
            }
            fb.close();
          }
        };
      }

      protected boolean isOneway() {
        return false;
      }

      public void start(I iface, getAllGroupMembers_args args, org.apache.thrift.async.AsyncMethodCallback<GroupMemberListResult> resultHandler) throws TException {
        iface.getAllGroupMembers(args.arg0, args.arg1,resultHandler);
      }
    }

  }

  public static class getAllGroupMembers_args implements org.apache.thrift.TBase<getAllGroupMembers_args, getAllGroupMembers_args._Fields>, java.io.Serializable, Cloneable, Comparable<getAllGroupMembers_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("getAllGroupMembers_args");

    private static final org.apache.thrift.protocol.TField ARG0_FIELD_DESC = new org.apache.thrift.protocol.TField("arg0", org.apache.thrift.protocol.TType.I64, (short)1);
    private static final org.apache.thrift.protocol.TField ARG1_FIELD_DESC = new org.apache.thrift.protocol.TField("arg1", org.apache.thrift.protocol.TType.I64, (short)2);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new getAllGroupMembers_argsStandardSchemeFactory());
      schemes.put(TupleScheme.class, new getAllGroupMembers_argsTupleSchemeFactory());
    }

    public long arg0; // required
    public long arg1; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      ARG0((short)1, "arg0"),
      ARG1((short)2, "arg1");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 1: // ARG0
            return ARG0;
          case 2: // ARG1
            return ARG1;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __ARG0_ISSET_ID = 0;
    private static final int __ARG1_ISSET_ID = 1;
    private byte __isset_bitfield = 0;
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.ARG0, new org.apache.thrift.meta_data.FieldMetaData("arg0", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
      tmpMap.put(_Fields.ARG1, new org.apache.thrift.meta_data.FieldMetaData("arg1", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(getAllGroupMembers_args.class, metaDataMap);
    }

    public getAllGroupMembers_args() {
    }

    public getAllGroupMembers_args(
      long arg0,
      long arg1)
    {
      this();
      this.arg0 = arg0;
      setArg0IsSet(true);
      this.arg1 = arg1;
      setArg1IsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getAllGroupMembers_args(getAllGroupMembers_args other) {
      __isset_bitfield = other.__isset_bitfield;
      this.arg0 = other.arg0;
      this.arg1 = other.arg1;
    }

    public getAllGroupMembers_args deepCopy() {
      return new getAllGroupMembers_args(this);
    }

    @Override
    public void clear() {
      setArg0IsSet(false);
      this.arg0 = 0;
      setArg1IsSet(false);
      this.arg1 = 0;
    }

    public long getArg0() {
      return this.arg0;
    }

    public getAllGroupMembers_args setArg0(long arg0) {
      this.arg0 = arg0;
      setArg0IsSet(true);
      return this;
    }

    public void unsetArg0() {
      __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ARG0_ISSET_ID);
    }

    /** Returns true if field arg0 is set (has been assigned a value) and false otherwise */
    public boolean isSetArg0() {
      return EncodingUtils.testBit(__isset_bitfield, __ARG0_ISSET_ID);
    }

    public void setArg0IsSet(boolean value) {
      __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ARG0_ISSET_ID, value);
    }

    public long getArg1() {
      return this.arg1;
    }

    public getAllGroupMembers_args setArg1(long arg1) {
      this.arg1 = arg1;
      setArg1IsSet(true);
      return this;
    }

    public void unsetArg1() {
      __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ARG1_ISSET_ID);
    }

    /** Returns true if field arg1 is set (has been assigned a value) and false otherwise */
    public boolean isSetArg1() {
      return EncodingUtils.testBit(__isset_bitfield, __ARG1_ISSET_ID);
    }

    public void setArg1IsSet(boolean value) {
      __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ARG1_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case ARG0:
        if (value == null) {
          unsetArg0();
        } else {
          setArg0((Long)value);
        }
        break;

      case ARG1:
        if (value == null) {
          unsetArg1();
        } else {
          setArg1((Long)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case ARG0:
        return getArg0();

      case ARG1:
        return getArg1();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case ARG0:
        return isSetArg0();
      case ARG1:
        return isSetArg1();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getAllGroupMembers_args)
        return this.equals((getAllGroupMembers_args)that);
      return false;
    }

    public boolean equals(getAllGroupMembers_args that) {
      if (that == null)
        return false;

      boolean this_present_arg0 = true;
      boolean that_present_arg0 = true;
      if (this_present_arg0 || that_present_arg0) {
        if (!(this_present_arg0 && that_present_arg0))
          return false;
        if (this.arg0 != that.arg0)
          return false;
      }

      boolean this_present_arg1 = true;
      boolean that_present_arg1 = true;
      if (this_present_arg1 || that_present_arg1) {
        if (!(this_present_arg1 && that_present_arg1))
          return false;
        if (this.arg1 != that.arg1)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_arg0 = true;
      list.add(present_arg0);
      if (present_arg0)
        list.add(arg0);

      boolean present_arg1 = true;
      list.add(present_arg1);
      if (present_arg1)
        list.add(arg1);

      return list.hashCode();
    }

    @Override
    public int compareTo(getAllGroupMembers_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetArg0()).compareTo(other.isSetArg0());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetArg0()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.arg0, other.arg0);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetArg1()).compareTo(other.isSetArg1());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetArg1()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.arg1, other.arg1);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getAllGroupMembers_args(");
      boolean first = true;

      sb.append("arg0:");
      sb.append(this.arg0);
      first = false;
      if (!first) sb.append(", ");
      sb.append("arg1:");
      sb.append(this.arg1);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
        __isset_bitfield = 0;
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class getAllGroupMembers_argsStandardSchemeFactory implements SchemeFactory {
      public getAllGroupMembers_argsStandardScheme getScheme() {
        return new getAllGroupMembers_argsStandardScheme();
      }
    }

    private static class getAllGroupMembers_argsStandardScheme extends StandardScheme<getAllGroupMembers_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, getAllGroupMembers_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // ARG0
              if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
                struct.arg0 = iprot.readI64();
                struct.setArg0IsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 2: // ARG1
              if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
                struct.arg1 = iprot.readI64();
                struct.setArg1IsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, getAllGroupMembers_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        oprot.writeFieldBegin(ARG0_FIELD_DESC);
        oprot.writeI64(struct.arg0);
        oprot.writeFieldEnd();
        oprot.writeFieldBegin(ARG1_FIELD_DESC);
        oprot.writeI64(struct.arg1);
        oprot.writeFieldEnd();
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class getAllGroupMembers_argsTupleSchemeFactory implements SchemeFactory {
      public getAllGroupMembers_argsTupleScheme getScheme() {
        return new getAllGroupMembers_argsTupleScheme();
      }
    }

    private static class getAllGroupMembers_argsTupleScheme extends TupleScheme<getAllGroupMembers_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, getAllGroupMembers_args struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetArg0()) {
          optionals.set(0);
        }
        if (struct.isSetArg1()) {
          optionals.set(1);
        }
        oprot.writeBitSet(optionals, 2);
        if (struct.isSetArg0()) {
          oprot.writeI64(struct.arg0);
        }
        if (struct.isSetArg1()) {
          oprot.writeI64(struct.arg1);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, getAllGroupMembers_args struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(2);
        if (incoming.get(0)) {
          struct.arg0 = iprot.readI64();
          struct.setArg0IsSet(true);
        }
        if (incoming.get(1)) {
          struct.arg1 = iprot.readI64();
          struct.setArg1IsSet(true);
        }
      }
    }

  }

  public static class getAllGroupMembers_result implements org.apache.thrift.TBase<getAllGroupMembers_result, getAllGroupMembers_result._Fields>, java.io.Serializable, Cloneable, Comparable<getAllGroupMembers_result>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("getAllGroupMembers_result");

    private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.STRUCT, (short)0);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new getAllGroupMembers_resultStandardSchemeFactory());
      schemes.put(TupleScheme.class, new getAllGroupMembers_resultTupleSchemeFactory());
    }

    public GroupMemberListResult success; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      SUCCESS((short)0, "success");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 0: // SUCCESS
            return SUCCESS;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, GroupMemberListResult.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(getAllGroupMembers_result.class, metaDataMap);
    }

    public getAllGroupMembers_result() {
    }

    public getAllGroupMembers_result(
      GroupMemberListResult success)
    {
      this();
      this.success = success;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getAllGroupMembers_result(getAllGroupMembers_result other) {
      if (other.isSetSuccess()) {
        this.success = new GroupMemberListResult(other.success);
      }
    }

    public getAllGroupMembers_result deepCopy() {
      return new getAllGroupMembers_result(this);
    }

    @Override
    public void clear() {
      this.success = null;
    }

    public GroupMemberListResult getSuccess() {
      return this.success;
    }

    public getAllGroupMembers_result setSuccess(GroupMemberListResult success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been assigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((GroupMemberListResult)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getAllGroupMembers_result)
        return this.equals((getAllGroupMembers_result)that);
      return false;
    }

    public boolean equals(getAllGroupMembers_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_success = true && (isSetSuccess());
      list.add(present_success);
      if (present_success)
        list.add(success);

      return list.hashCode();
    }

    @Override
    public int compareTo(getAllGroupMembers_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(other.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, other.success);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
      }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getAllGroupMembers_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
      if (success != null) {
        success.validate();
      }
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class getAllGroupMembers_resultStandardSchemeFactory implements SchemeFactory {
      public getAllGroupMembers_resultStandardScheme getScheme() {
        return new getAllGroupMembers_resultStandardScheme();
      }
    }

    private static class getAllGroupMembers_resultStandardScheme extends StandardScheme<getAllGroupMembers_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, getAllGroupMembers_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 0: // SUCCESS
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.success = new GroupMemberListResult();
                struct.success.read(iprot);
                struct.setSuccessIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, getAllGroupMembers_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.success != null) {
          oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
          struct.success.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class getAllGroupMembers_resultTupleSchemeFactory implements SchemeFactory {
      public getAllGroupMembers_resultTupleScheme getScheme() {
        return new getAllGroupMembers_resultTupleScheme();
      }
    }

    private static class getAllGroupMembers_resultTupleScheme extends TupleScheme<getAllGroupMembers_result> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, getAllGroupMembers_result struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetSuccess()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetSuccess()) {
          struct.success.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, getAllGroupMembers_result struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.success = new GroupMemberListResult();
          struct.success.read(iprot);
          struct.setSuccessIsSet(true);
        }
      }
    }

  }

}
