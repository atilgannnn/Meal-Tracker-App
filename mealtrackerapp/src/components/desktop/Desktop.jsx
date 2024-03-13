import React, { useState, useEffect } from 'react';
import {
  Form,
  FormGroup,
  Button,
  Table,
  Pagination,
  PaginationItem,
  PaginationLink,
} from 'reactstrap';
import logo from '../../images/tm-logo.png';
import Select from 'react-select';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrashCan } from '@fortawesome/free-regular-svg-icons';
import { useNavigate } from 'react-router-dom';

export default function Desktop({ meal, time }) {
  const [inputValueMeal, setInputValueMeal] = useState('');
  const [inputValueTime, setInputValueTime] = useState('');
  const [items, setItems] = useState([]);
  const [currentUser, setCurrentUser] = useState('');
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 5;
  const navigate = useNavigate();

  useEffect(() => {
    const storedItems = JSON.parse(localStorage.getItem('items'));
    if (
      storedItems &&
      currentUser !== localStorage.getItem('currentUsername')
    ) {
      setItems(storedItems);
      setCurrentUser(localStorage.getItem('currentUsername'));
    }
  }, []);

  const handleChange = (selectedOption, { name }) => {
    if (name === 'meal') {
      setInputValueMeal(selectedOption);
    } else if (name === 'time') {
      setInputValueTime(selectedOption);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (
      !inputValueMeal.value ||
      !inputValueTime.value ||
      !localStorage.getItem('currentUsername')
    )
      return;

    const newItem = {
      meal: inputValueMeal.value,
      time: inputValueTime.value,
      username: localStorage.getItem('currentUsername'),
    };
    const updatedItems = [...items, newItem];
    setCurrentUser(localStorage.getItem('currentUsername'));
    setItems(updatedItems);
    setInputValueMeal('');
    setInputValueTime('');

    localStorage.setItem('items', JSON.stringify(updatedItems));
  };

  const deleteItem = (index) => {
    const updatedItems = items.filter((item, i) => i !== index);
    setItems(updatedItems);
    localStorage.setItem('items', JSON.stringify(updatedItems));
  };

  const handleSignOut = () => {
    localStorage.setItem('isLoggedIn', 'false');
    navigate('/');
  };

  const mealOptions = [
    { value: 'Breakfast', label: 'Breakfast' },
    { value: 'Lunch', label: 'Lunch' },
    { value: 'Dinner', label: 'Dinner' },
  ];

  const timeOptions = [
    { value: '00:00 - 01:00', label: '00:00 - 01:00' },
    { value: '01:00 - 02:00', label: '01:00 - 02:00' },
    { value: '02:00 - 03:00', label: '02:00 - 03:00' },
    { value: '03:00 - 04:00', label: '03:00 - 04:00' },
    { value: '04:00 - 05:00', label: '04:00 - 05:00' },
    { value: '05:00 - 06:00', label: '05:00 - 06:00' },
    { value: '06:00 - 07:00', label: '06:00 - 07:00' },
    { value: '07:00 - 08:00', label: '07:00 - 08:00' },
    { value: '08:00 - 09:00', label: '08:00 - 09:00' },
    { value: '09:00 - 10:00', label: '09:00 - 10:00' },
    { value: '10:00 - 11:00', label: '10:00 - 11:00' },
    { value: '11:00 - 12:00', label: '11:00 - 12:00' },
    { value: '12:00 - 13:00', label: '12:00 - 13:00' },
    { value: '13:00 - 14:00', label: '13:00 - 14:00' },
    { value: '14:00 - 15:00', label: '14:00 - 15:00' },
    { value: '15:00 - 16:00', label: '15:00 - 16:00' },
    { value: '16:00 - 17:00', label: '16:00 - 17:00' },
    { value: '17:00 - 18:00', label: '17:00 - 18:00' },
    { value: '18:00 - 19:00', label: '18:00 - 19:00' },
    { value: '19:00 - 20:00', label: '19:00 - 20:00' },
    { value: '20:00 - 21:00', label: '20:00 - 21:00' },
    { value: '21:00 - 22:00', label: '21:00 - 22:00' },
    { value: '22:00 - 23:00', label: '22:00 - 23:00' },
    { value: '23:00 - 23:59', label: '23:00 - 23:59' },
  ];

  const totalPages = Math.ceil(items.length / itemsPerPage);

  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentItems = items.slice(indexOfFirstItem, indexOfLastItem);

  const renderItems = currentItems.map((item, index) => (
    <tr key={index} className='table-primary'>
      <td>{currentUser}</td>
      <td>{item.meal}</td>
      <td>{item.time}</td>
      <td>
        <Button className='deletebtn' onClick={() => deleteItem(index)}>
          <FontAwesomeIcon icon={faTrashCan} />
        </Button>
      </td>
    </tr>
  ));

  const renderPageNumbers = Array.from({ length: totalPages }, (_, i) => (
    <PaginationItem key={i} active={i + 1 === currentPage}>
      <PaginationLink onClick={() => setCurrentPage(i + 1)}>
        {i + 1}
      </PaginationLink>
    </PaginationItem>
  ));

  return (
    <div className='desktop'>
      <div className='section-div'>
        <div className='img-div'>
          <img src={logo} />
        </div>
        <Form className='form' onSubmit={handleSubmit}>
          <FormGroup>
            <Select
              options={mealOptions}
              placeholder='Select Meal'
              className='selector'
              value={inputValueMeal}
              onChange={(selectedOption) =>
                handleChange(selectedOption, { name: 'meal' })
              }
            />
          </FormGroup>
          <FormGroup>
            <Select
              options={timeOptions}
              placeholder='Select Time'
              className='selector'
              value={inputValueTime}
              onChange={(selectedOption) =>
                handleChange(selectedOption, { name: 'time' })
              }
            />
          </FormGroup>
          <div>
            <Button type='submit' className='button'>
              Add
            </Button>
          </div>
          <div>
            <Button className='button' onClick={handleSignOut}>
              Sign out
            </Button>
          </div>
        </Form>
      </div>
      <div className='time section-div'>
        <Table className='time-table' bordered>
          <thead>
            <tr>
              <th>Username</th>
              <th>Meal Type</th>
              <th>Time</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>{renderItems}</tbody>
        </Table>

        <Pagination>
          <PaginationItem disabled={currentPage <= 1}>
            <PaginationLink onClick={() => setCurrentPage(currentPage - 1)}>
              Previous
            </PaginationLink>
          </PaginationItem>
          {renderPageNumbers}
          <PaginationItem disabled={currentPage >= totalPages}>
            <PaginationLink onClick={() => setCurrentPage(currentPage + 1)}>
              Next
            </PaginationLink>
          </PaginationItem>
        </Pagination>
      </div>
    </div>
  );
}
