//
// Copyright (c) 2016-2022 Deephaven Data Labs and Patent Pending

// Code generated by protoc-gen-go. DO NOT EDIT.
// versions:
// 	protoc-gen-go v1.28.1
// 	protoc        v3.17.3
// source: deephaven/proto/object.proto

package object

import (
	ticket "github.com/deephaven/deephaven-core/go/internal/proto/ticket"
	protoreflect "google.golang.org/protobuf/reflect/protoreflect"
	protoimpl "google.golang.org/protobuf/runtime/protoimpl"
	reflect "reflect"
	sync "sync"
)

const (
	// Verify that this generated code is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(20 - protoimpl.MinVersion)
	// Verify that runtime/protoimpl is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(protoimpl.MaxVersion - 20)
)

type FetchObjectRequest struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	SourceId *ticket.TypedTicket `protobuf:"bytes,1,opt,name=source_id,json=sourceId,proto3" json:"source_id,omitempty"`
}

func (x *FetchObjectRequest) Reset() {
	*x = FetchObjectRequest{}
	if protoimpl.UnsafeEnabled {
		mi := &file_deephaven_proto_object_proto_msgTypes[0]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *FetchObjectRequest) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*FetchObjectRequest) ProtoMessage() {}

func (x *FetchObjectRequest) ProtoReflect() protoreflect.Message {
	mi := &file_deephaven_proto_object_proto_msgTypes[0]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use FetchObjectRequest.ProtoReflect.Descriptor instead.
func (*FetchObjectRequest) Descriptor() ([]byte, []int) {
	return file_deephaven_proto_object_proto_rawDescGZIP(), []int{0}
}

func (x *FetchObjectRequest) GetSourceId() *ticket.TypedTicket {
	if x != nil {
		return x.SourceId
	}
	return nil
}

type FetchObjectResponse struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Type          string                `protobuf:"bytes,1,opt,name=type,proto3" json:"type,omitempty"`
	Data          []byte                `protobuf:"bytes,2,opt,name=data,proto3" json:"data,omitempty"`
	TypedExportId []*ticket.TypedTicket `protobuf:"bytes,3,rep,name=typed_export_id,json=typedExportId,proto3" json:"typed_export_id,omitempty"`
}

func (x *FetchObjectResponse) Reset() {
	*x = FetchObjectResponse{}
	if protoimpl.UnsafeEnabled {
		mi := &file_deephaven_proto_object_proto_msgTypes[1]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *FetchObjectResponse) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*FetchObjectResponse) ProtoMessage() {}

func (x *FetchObjectResponse) ProtoReflect() protoreflect.Message {
	mi := &file_deephaven_proto_object_proto_msgTypes[1]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use FetchObjectResponse.ProtoReflect.Descriptor instead.
func (*FetchObjectResponse) Descriptor() ([]byte, []int) {
	return file_deephaven_proto_object_proto_rawDescGZIP(), []int{1}
}

func (x *FetchObjectResponse) GetType() string {
	if x != nil {
		return x.Type
	}
	return ""
}

func (x *FetchObjectResponse) GetData() []byte {
	if x != nil {
		return x.Data
	}
	return nil
}

func (x *FetchObjectResponse) GetTypedExportId() []*ticket.TypedTicket {
	if x != nil {
		return x.TypedExportId
	}
	return nil
}

var File_deephaven_proto_object_proto protoreflect.FileDescriptor

var file_deephaven_proto_object_proto_rawDesc = []byte{
	0x0a, 0x1c, 0x64, 0x65, 0x65, 0x70, 0x68, 0x61, 0x76, 0x65, 0x6e, 0x2f, 0x70, 0x72, 0x6f, 0x74,
	0x6f, 0x2f, 0x6f, 0x62, 0x6a, 0x65, 0x63, 0x74, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x12, 0x21,
	0x69, 0x6f, 0x2e, 0x64, 0x65, 0x65, 0x70, 0x68, 0x61, 0x76, 0x65, 0x6e, 0x2e, 0x70, 0x72, 0x6f,
	0x74, 0x6f, 0x2e, 0x62, 0x61, 0x63, 0x6b, 0x70, 0x6c, 0x61, 0x6e, 0x65, 0x2e, 0x67, 0x72, 0x70,
	0x63, 0x1a, 0x1c, 0x64, 0x65, 0x65, 0x70, 0x68, 0x61, 0x76, 0x65, 0x6e, 0x2f, 0x70, 0x72, 0x6f,
	0x74, 0x6f, 0x2f, 0x74, 0x69, 0x63, 0x6b, 0x65, 0x74, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x22,
	0x61, 0x0a, 0x12, 0x46, 0x65, 0x74, 0x63, 0x68, 0x4f, 0x62, 0x6a, 0x65, 0x63, 0x74, 0x52, 0x65,
	0x71, 0x75, 0x65, 0x73, 0x74, 0x12, 0x4b, 0x0a, 0x09, 0x73, 0x6f, 0x75, 0x72, 0x63, 0x65, 0x5f,
	0x69, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x0b, 0x32, 0x2e, 0x2e, 0x69, 0x6f, 0x2e, 0x64, 0x65,
	0x65, 0x70, 0x68, 0x61, 0x76, 0x65, 0x6e, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x2e, 0x62, 0x61,
	0x63, 0x6b, 0x70, 0x6c, 0x61, 0x6e, 0x65, 0x2e, 0x67, 0x72, 0x70, 0x63, 0x2e, 0x54, 0x79, 0x70,
	0x65, 0x64, 0x54, 0x69, 0x63, 0x6b, 0x65, 0x74, 0x52, 0x08, 0x73, 0x6f, 0x75, 0x72, 0x63, 0x65,
	0x49, 0x64, 0x22, 0x95, 0x01, 0x0a, 0x13, 0x46, 0x65, 0x74, 0x63, 0x68, 0x4f, 0x62, 0x6a, 0x65,
	0x63, 0x74, 0x52, 0x65, 0x73, 0x70, 0x6f, 0x6e, 0x73, 0x65, 0x12, 0x12, 0x0a, 0x04, 0x74, 0x79,
	0x70, 0x65, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x04, 0x74, 0x79, 0x70, 0x65, 0x12, 0x12,
	0x0a, 0x04, 0x64, 0x61, 0x74, 0x61, 0x18, 0x02, 0x20, 0x01, 0x28, 0x0c, 0x52, 0x04, 0x64, 0x61,
	0x74, 0x61, 0x12, 0x56, 0x0a, 0x0f, 0x74, 0x79, 0x70, 0x65, 0x64, 0x5f, 0x65, 0x78, 0x70, 0x6f,
	0x72, 0x74, 0x5f, 0x69, 0x64, 0x18, 0x03, 0x20, 0x03, 0x28, 0x0b, 0x32, 0x2e, 0x2e, 0x69, 0x6f,
	0x2e, 0x64, 0x65, 0x65, 0x70, 0x68, 0x61, 0x76, 0x65, 0x6e, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f,
	0x2e, 0x62, 0x61, 0x63, 0x6b, 0x70, 0x6c, 0x61, 0x6e, 0x65, 0x2e, 0x67, 0x72, 0x70, 0x63, 0x2e,
	0x54, 0x79, 0x70, 0x65, 0x64, 0x54, 0x69, 0x63, 0x6b, 0x65, 0x74, 0x52, 0x0d, 0x74, 0x79, 0x70,
	0x65, 0x64, 0x45, 0x78, 0x70, 0x6f, 0x72, 0x74, 0x49, 0x64, 0x32, 0x8f, 0x01, 0x0a, 0x0d, 0x4f,
	0x62, 0x6a, 0x65, 0x63, 0x74, 0x53, 0x65, 0x72, 0x76, 0x69, 0x63, 0x65, 0x12, 0x7e, 0x0a, 0x0b,
	0x46, 0x65, 0x74, 0x63, 0x68, 0x4f, 0x62, 0x6a, 0x65, 0x63, 0x74, 0x12, 0x35, 0x2e, 0x69, 0x6f,
	0x2e, 0x64, 0x65, 0x65, 0x70, 0x68, 0x61, 0x76, 0x65, 0x6e, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f,
	0x2e, 0x62, 0x61, 0x63, 0x6b, 0x70, 0x6c, 0x61, 0x6e, 0x65, 0x2e, 0x67, 0x72, 0x70, 0x63, 0x2e,
	0x46, 0x65, 0x74, 0x63, 0x68, 0x4f, 0x62, 0x6a, 0x65, 0x63, 0x74, 0x52, 0x65, 0x71, 0x75, 0x65,
	0x73, 0x74, 0x1a, 0x36, 0x2e, 0x69, 0x6f, 0x2e, 0x64, 0x65, 0x65, 0x70, 0x68, 0x61, 0x76, 0x65,
	0x6e, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x2e, 0x62, 0x61, 0x63, 0x6b, 0x70, 0x6c, 0x61, 0x6e,
	0x65, 0x2e, 0x67, 0x72, 0x70, 0x63, 0x2e, 0x46, 0x65, 0x74, 0x63, 0x68, 0x4f, 0x62, 0x6a, 0x65,
	0x63, 0x74, 0x52, 0x65, 0x73, 0x70, 0x6f, 0x6e, 0x73, 0x65, 0x22, 0x00, 0x42, 0x42, 0x48, 0x01,
	0x50, 0x01, 0x5a, 0x3c, 0x67, 0x69, 0x74, 0x68, 0x75, 0x62, 0x2e, 0x63, 0x6f, 0x6d, 0x2f, 0x64,
	0x65, 0x65, 0x70, 0x68, 0x61, 0x76, 0x65, 0x6e, 0x2f, 0x64, 0x65, 0x65, 0x70, 0x68, 0x61, 0x76,
	0x65, 0x6e, 0x2d, 0x63, 0x6f, 0x72, 0x65, 0x2f, 0x67, 0x6f, 0x2f, 0x69, 0x6e, 0x74, 0x65, 0x72,
	0x6e, 0x61, 0x6c, 0x2f, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x2f, 0x6f, 0x62, 0x6a, 0x65, 0x63, 0x74,
	0x62, 0x06, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x33,
}

var (
	file_deephaven_proto_object_proto_rawDescOnce sync.Once
	file_deephaven_proto_object_proto_rawDescData = file_deephaven_proto_object_proto_rawDesc
)

func file_deephaven_proto_object_proto_rawDescGZIP() []byte {
	file_deephaven_proto_object_proto_rawDescOnce.Do(func() {
		file_deephaven_proto_object_proto_rawDescData = protoimpl.X.CompressGZIP(file_deephaven_proto_object_proto_rawDescData)
	})
	return file_deephaven_proto_object_proto_rawDescData
}

var file_deephaven_proto_object_proto_msgTypes = make([]protoimpl.MessageInfo, 2)
var file_deephaven_proto_object_proto_goTypes = []interface{}{
	(*FetchObjectRequest)(nil),  // 0: io.deephaven.proto.backplane.grpc.FetchObjectRequest
	(*FetchObjectResponse)(nil), // 1: io.deephaven.proto.backplane.grpc.FetchObjectResponse
	(*ticket.TypedTicket)(nil),  // 2: io.deephaven.proto.backplane.grpc.TypedTicket
}
var file_deephaven_proto_object_proto_depIdxs = []int32{
	2, // 0: io.deephaven.proto.backplane.grpc.FetchObjectRequest.source_id:type_name -> io.deephaven.proto.backplane.grpc.TypedTicket
	2, // 1: io.deephaven.proto.backplane.grpc.FetchObjectResponse.typed_export_id:type_name -> io.deephaven.proto.backplane.grpc.TypedTicket
	0, // 2: io.deephaven.proto.backplane.grpc.ObjectService.FetchObject:input_type -> io.deephaven.proto.backplane.grpc.FetchObjectRequest
	1, // 3: io.deephaven.proto.backplane.grpc.ObjectService.FetchObject:output_type -> io.deephaven.proto.backplane.grpc.FetchObjectResponse
	3, // [3:4] is the sub-list for method output_type
	2, // [2:3] is the sub-list for method input_type
	2, // [2:2] is the sub-list for extension type_name
	2, // [2:2] is the sub-list for extension extendee
	0, // [0:2] is the sub-list for field type_name
}

func init() { file_deephaven_proto_object_proto_init() }
func file_deephaven_proto_object_proto_init() {
	if File_deephaven_proto_object_proto != nil {
		return
	}
	if !protoimpl.UnsafeEnabled {
		file_deephaven_proto_object_proto_msgTypes[0].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*FetchObjectRequest); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_deephaven_proto_object_proto_msgTypes[1].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*FetchObjectResponse); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
	}
	type x struct{}
	out := protoimpl.TypeBuilder{
		File: protoimpl.DescBuilder{
			GoPackagePath: reflect.TypeOf(x{}).PkgPath(),
			RawDescriptor: file_deephaven_proto_object_proto_rawDesc,
			NumEnums:      0,
			NumMessages:   2,
			NumExtensions: 0,
			NumServices:   1,
		},
		GoTypes:           file_deephaven_proto_object_proto_goTypes,
		DependencyIndexes: file_deephaven_proto_object_proto_depIdxs,
		MessageInfos:      file_deephaven_proto_object_proto_msgTypes,
	}.Build()
	File_deephaven_proto_object_proto = out.File
	file_deephaven_proto_object_proto_rawDesc = nil
	file_deephaven_proto_object_proto_goTypes = nil
	file_deephaven_proto_object_proto_depIdxs = nil
}
