// Generated by gencpp from file mindbot_msgs/SetCtrlState.msg
// DO NOT EDIT!


#ifndef MINDBOT_MSGS_MESSAGE_SETCTRLSTATE_H
#define MINDBOT_MSGS_MESSAGE_SETCTRLSTATE_H

#include <ros/service_traits.h>


#include <mindbot_msgs/SetCtrlStateRequest.h>
#include <mindbot_msgs/SetCtrlStateResponse.h>


namespace mindbot_msgs
{

struct SetCtrlState
{

typedef SetCtrlStateRequest Request;
typedef SetCtrlStateResponse Response;
Request request;
Response response;

typedef Request RequestType;
typedef Response ResponseType;

}; // struct SetCtrlState
} // namespace mindbot_msgs


namespace ros
{
namespace service_traits
{


template<>
struct MD5Sum< ::mindbot_msgs::SetCtrlState > {
  static const char* value()
  {
    return "60923bc5e5c8d30acc73ee0d6e56f3ab";
  }

  static const char* value(const ::mindbot_msgs::SetCtrlState&) { return value(); }
};

template<>
struct DataType< ::mindbot_msgs::SetCtrlState > {
  static const char* value()
  {
    return "mindbot_msgs/SetCtrlState";
  }

  static const char* value(const ::mindbot_msgs::SetCtrlState&) { return value(); }
};


// service_traits::MD5Sum< ::mindbot_msgs::SetCtrlStateRequest> should match
// service_traits::MD5Sum< ::mindbot_msgs::SetCtrlState >
template<>
struct MD5Sum< ::mindbot_msgs::SetCtrlStateRequest>
{
  static const char* value()
  {
    return MD5Sum< ::mindbot_msgs::SetCtrlState >::value();
  }
  static const char* value(const ::mindbot_msgs::SetCtrlStateRequest&)
  {
    return value();
  }
};

// service_traits::DataType< ::mindbot_msgs::SetCtrlStateRequest> should match
// service_traits::DataType< ::mindbot_msgs::SetCtrlState >
template<>
struct DataType< ::mindbot_msgs::SetCtrlStateRequest>
{
  static const char* value()
  {
    return DataType< ::mindbot_msgs::SetCtrlState >::value();
  }
  static const char* value(const ::mindbot_msgs::SetCtrlStateRequest&)
  {
    return value();
  }
};

// service_traits::MD5Sum< ::mindbot_msgs::SetCtrlStateResponse> should match
// service_traits::MD5Sum< ::mindbot_msgs::SetCtrlState >
template<>
struct MD5Sum< ::mindbot_msgs::SetCtrlStateResponse>
{
  static const char* value()
  {
    return MD5Sum< ::mindbot_msgs::SetCtrlState >::value();
  }
  static const char* value(const ::mindbot_msgs::SetCtrlStateResponse&)
  {
    return value();
  }
};

// service_traits::DataType< ::mindbot_msgs::SetCtrlStateResponse> should match
// service_traits::DataType< ::mindbot_msgs::SetCtrlState >
template<>
struct DataType< ::mindbot_msgs::SetCtrlStateResponse>
{
  static const char* value()
  {
    return DataType< ::mindbot_msgs::SetCtrlState >::value();
  }
  static const char* value(const ::mindbot_msgs::SetCtrlStateResponse&)
  {
    return value();
  }
};

} // namespace service_traits
} // namespace ros

#endif // MINDBOT_MSGS_MESSAGE_SETCTRLSTATE_H