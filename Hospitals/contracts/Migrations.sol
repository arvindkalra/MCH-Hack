pragma solidity ^0.4.11;

contract SOS{
  address private creator;
  // address in the mappigs is of the hospital
  mapping(address => Hospital) hospitalDetail;
  //triggered when the availablility of hospitalchanges
  event incrementDone(address hospital,uint new_availability);
  event InformHospital(address hospital,);
  event InformPatient;

  struct Hospital{
    uint hospitalCapacity;
    uint hospitalAvailability;
    uint uniqid;
  }
  function SOS() public {
    creator = msg.sender;
  }
  function  addHospital(address _hospital, uint _capacity, uint _availability) public{
    require(msg.sender == creator);
    require(hospitalDetail[_hospital].hospitalCapacity ==0);
    hospitalDetail[_hospital] =
    hospitalDetail[_hospital] = Hospital(_capacity, _availability, );

  }
  function increment() public{
    // does hospital exist in the map
    require(hospitalDetail[msg.sender].hospitalCapacity != 0);
    require(hospitalDetail[msg.sender].hospitalAvailability < hospitalDetail[msg.sender].hospitalCapacity);
    // to make sure that number of available beds does not exceed capacity
    hospitalDetail[msg.sender].hospitalAvailability +=1;
    incrementDone(msg.sender, hospitalDetail[msg.sender.hospitalAvailability]);


  }
  function decrement(address _hospital) public returns (bool){
    require(msg.sender == creator);
    require(hospitalDetail[_hospital].hospitalCapacity != 0);
    if(hospitalDetail[_hospital].hospitalAvailability > 0){
      hospitalDetail[_hospital].hospitalAvailability -=1;

      return true;
    }else{
      return false;
    }

  }

}