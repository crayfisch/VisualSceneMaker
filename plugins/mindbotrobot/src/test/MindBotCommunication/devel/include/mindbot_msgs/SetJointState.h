// Generated by gencpp from file mindbot_msgs/SetJointState.msg
// DO NOT EDIT!


#ifndef MINDBOT_MSGS_MESSAGE_SETJOINTSTATE_H
#define MINDBOT_MSGS_MESSAGE_SETJOINTSTATE_H

#include <ros/service_traits.h>


#include <mindbot_msgs/SetJointStateRequest.h>
#include <mindbot_msgs/SetJointStateResponse.h>


namespace mindbot_msgs
{

struct SetJointState
{

typedef SetJointStateRequest Request;
typedef SetJointStateResponse Response;
Request request;
Response response;

typedef Request RequestType;
typedef Response ResponseType;

}; // struct SetJointState
} // namespace mindbot_msgs


namespace ros
{
namespace service_traits
{


template<>
struct MD5Sum< ::mindbot_msgs::SetJointState > {
  static const char* value()
  {
    return "2dd47abbafae7d0f1f325f1e048e28c7";
  }

  static const char* value(const ::mindbot_msgs::SetJointState&) { return value(); }
};

template<>
struct DataType< ::mindbot_msgs::SetJointState > {
  static const char* value()
  {
    return "mindbot_msgs/SetJointState";
  }

  static const char* value(const ::mindbot_msgs::SetJointState&) { return value(); }
};


// service_traits::MD5Sum< ::mindbot_msgs::SetJointStateRequest> should match
// service_traits::MD5Sum< ::mindbot_msgs::SetJointState >
template<>
struct MD5Sum< ::mindbot_msgs::SetJointStateRequest>
{
  static const char* value()
  {
    return MD5Sum< ::mindbot_msgs::SetJointState >::value();
  }
  static const char* value(const ::mindbot_msgs::SetJointStateRequest&)
  {
    return value();
  }
};

// service_traits::DataType< ::mindbot_msgs::SetJointStateRequest> should match
// service_traits::DataType< ::mindbot_msgs::SetJointState >
template<>
struct DataType< ::mindbot_msgs::SetJointStateRequest>
{
  static const char* value()
  {
    return DataType< ::mindbot_msgs::SetJointState >::value();
  }
  static const char* value(const ::mindbot_msgs::SetJointStateRequest&)
  {
    return value();
  }
};

// service_traits::MD5Sum< ::mindbot_msgs::SetJointStateResponse> should match
// service_traits::MD5Sum< ::mindbot_msgs::SetJointState >
template<>
struct MD5Sum< ::mindbot_msgs::SetJointStateResponse>
{
  static const char* value()
  {
    return MD5Sum< ::mindbot_msgs::SetJointState >::value();
  }
  static const char* value(const ::mindbot_msgs::SetJointStateResponse&)
  {
    return value();
  }
};

// service_traits::DataType< ::mindbot_msgs::SetJointStateResponse> should match
// service_traits::DataType< ::mindbot_msgs::SetJointState >
template<>
struct DataType< ::mindbot_msgs::SetJointStateResponse>
{
  static const char* value()
  {
    return DataType< ::mindbot_msgs::SetJointState >::value();
  }
  static const char* value(const ::mindbot_msgs::SetJointStateResponse&)
  {
    return value();
  }
};

} // namespace service_traits
} // namespace ros

#endif // MINDBOT_MSGS_MESSAGE_SETJOINTSTATE_H
